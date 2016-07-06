package com.cn.jdshow.common.quartz;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class JOBImpl implements Job{

	Logger logger = Logger.getLogger(JOBImpl.class);
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDetailExtend jextend = (JobDetailExtend) context.getJobDetail();
		logger.info("开始执行定时任务【" + jextend.getName() + "】");
		ITaskExecute exe = jextend.getTaskExecute();
		exe.doit();
	}

}
