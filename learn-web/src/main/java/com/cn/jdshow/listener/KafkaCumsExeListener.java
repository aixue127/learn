package com.cn.jdshow.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.cn.jdshow.common.constant.kafkaTopicConstant;
import com.cn.jdshow.common.kafka.ConsumerTemplate;
import com.cn.jdshow.kafka.ProdTypeDelExecute;

public class KafkaCumsExeListener implements ServletContextListener{

	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		ConsumerTemplate.start(context, kafkaTopicConstant.JDSHOW_KAFKA_PRODTYPDEL, ProdTypeDelExecute.class);
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
