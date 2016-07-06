package com.cn.jdshow.common.quartz;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.cn.jdshow.common.constant.TFConstant;
import com.cn.jdshow.common.mongodb.MongoDBSource;
import com.cn.jdshow.common.util.PropertyUtil;
import com.cn.jdshow.domain.QuartzTask;

@Component("schedulerInfoTemplate")
public class SchedulerInfoTemplate implements ApplicationContextAware, ISchedulerInfoTemplate {

	Logger logger = Logger.getLogger(SchedulerInfoTemplate.class);
	
	public static final StdSchedulerFactory schfactory = initFactory();
	public static final Scheduler seheduler =  initScheduler();
	
	ApplicationContext applicationContext;
	
	
	public static StdSchedulerFactory initFactory() {
		try {
			StdSchedulerFactory tmpschfactory = new StdSchedulerFactory(PropertyUtil.quartzProperties);
			
			return tmpschfactory;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Scheduler initScheduler() {
		try {
			Scheduler tmpseheduler = schfactory.getScheduler();
			tmpseheduler.clear();
			return tmpseheduler;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*public static void init() {
		try {
			if(schfactory == null) {
				schfactory = new StdSchedulerFactory(QUARTZ_FILE);
			}
			if(seheduler == null) {
				seheduler = schfactory.getScheduler();
				seheduler.clear();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}*/
	
	
	/**
	 * 开始执行定时任务
	 * @throws UnknownHostException 
	 * @Description: TODO
	 * @date 2016年5月26日 下午2:17:01
	 */
	public void start() throws Exception {
		// 获取本机IP地址
		String ip = InetAddress.getLocalHost().getHostAddress();
		List<QuartzTask> taskList = MongoDBSource.getMongoTemplate().find(new Query(Criteria.where("ip").is(ip)), QuartzTask.class);
		for(QuartzTask task : taskList) {
			this.addTask(task);
		}
		seheduler.start();
	}
	
	public void addTask(QuartzTask task) throws Exception {
		JobDetailExtend jobDetail = new JobDetailExtend();
		jobDetail.setName(task.getJobName());
		jobDetail.setGroup(task.getJobGroup());
		jobDetail.setKey(new JobKey(task.getJobName(), task.getJobGroup()));
		if(jobDetail.getGroup().equals(TFConstant.QUARTZ_GROUP1)) {
			jobDetail.setJobClass(JOBExe1.class);
		} else {
			jobDetail.setJobClass(JOBExe2.class);
		}
		
		jobDetail.setTaskExecute((ITaskExecute) applicationContext.getBean(task.getTaskBean()));
		CronTriggerImpl trigger = new CronTriggerImpl();
		trigger.setName(jobDetail.getName());
		trigger.setCronExpression(task.getTaskCron());
		logger.info("启动定时任务Name:【"+task.getJobName()+"】,Group:【"+task.getJobGroup()+"】");
		seheduler.scheduleJob(jobDetail, trigger);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	/**
	 * 
	 * @Description: 所有正在执行执行中的任务
	 * @date 2016年5月26日 下午3:37:04
	 */
	public List<String> getEnableTaskName() throws Exception {
		Collection<Scheduler> colls = schfactory.getAllSchedulers();
		List<String> enables = new ArrayList<>();
		for(Scheduler s : colls) {
			Set<JobKey> jobkeys = s.getJobKeys(GroupMatcher.anyJobGroup());
			for(JobKey jk : jobkeys) {
				enables.add(jk.getName());
			}
		}
		return enables;
	}
	
	public void reCallTask(QuartzTask task) throws Exception {
		this.sotpTask(task);
		this.addTask(task);
	}
	
	public void sotpTask(QuartzTask task) throws Exception {
		JobKey key = new JobKey(task.getJobName(), task.getJobGroup());
		seheduler.deleteJob(key);
		logger.info("删除任务Name【"+task.getJobName()+"】,Group:【"+task.getJobGroup()+"】");
	}
	
	public void down() throws Exception {
		seheduler.shutdown();
	}
}
