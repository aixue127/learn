package com.cn.jdshow.common.quartz;

import java.util.List;

import com.cn.jdshow.domain.QuartzTask;

public interface ISchedulerInfoTemplate {

	void start() throws Exception;
	
	void addTask(QuartzTask task) throws Exception;
	
	List<String> getEnableTaskName() throws Exception;
	
	void reCallTask(QuartzTask task) throws Exception;
	
	void down() throws Exception;
	
	void sotpTask(QuartzTask task) throws Exception;
}
