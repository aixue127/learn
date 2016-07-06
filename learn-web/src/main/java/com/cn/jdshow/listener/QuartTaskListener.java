package com.cn.jdshow.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.cn.jdshow.common.quartz.ISchedulerInfoTemplate;

public class QuartTaskListener implements ServletContextListener{

	Logger logger = Logger.getLogger(QuartTaskListener.class);
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			
			logger.info("---------------->start quartz task<----------------");
			getInfoTemplate(sce).start();
			logger.info("---------------->end quartz task<----------------");
		} catch(Exception e) {
			logger.info("---------------->error quartz task<----------------");
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		try {
			
			getInfoTemplate(sce).down();
			logger.info("---------------->destroyed quartz task success<----------------");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private ISchedulerInfoTemplate getInfoTemplate(ServletContextEvent sce) {
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		ISchedulerInfoTemplate schedulerInfoTemplate = (ISchedulerInfoTemplate)context.getBean("schedulerInfoTemplate");
		return schedulerInfoTemplate;
	}
}
