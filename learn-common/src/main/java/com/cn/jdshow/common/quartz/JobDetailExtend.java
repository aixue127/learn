package com.cn.jdshow.common.quartz;

import org.quartz.impl.JobDetailImpl;

public class JobDetailExtend extends JobDetailImpl {

	// 设置执行的Bean
	private ITaskExecute taskExecute;
	
	public ITaskExecute getTaskExecute() {
		return taskExecute;
	}

	public void setTaskExecute(ITaskExecute taskExecute) {
		this.taskExecute = taskExecute;
	}
	
}
