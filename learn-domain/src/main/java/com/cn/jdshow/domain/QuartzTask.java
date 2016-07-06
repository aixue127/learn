package com.cn.jdshow.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 
 * @Description: 定时任务管理
 * @author mingxin.lu
 * @date 2016年6月12日 下午2:43:56   @version V1.0
 */
@Document(collection="QuartzTask")
@TypeAlias("QuartzTask")
public class QuartzTask implements Serializable{
	
	@Id
	private int id;
	@Field(value="ip")
	private String ip;
	@Field(value="taskType")
	private String taskType;
	@Field(value="taskBean")
	private String taskBean;
	@Field(value="taskCron")
	private String taskCron;
	@Field(value="status")
	private String status;
	@Field(value="jobName")
	private String jobName;
	@Field(value="jobGroup")
	private String jobGroup;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	public String getTaskBean() {
		return taskBean;
	}
	public void setTaskBean(String taskBean) {
		this.taskBean = taskBean;
	}
	public String getTaskCron() {
		return taskCron;
	}
	public void setTaskCron(String taskCron) {
		this.taskCron = taskCron;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobGroup() {
		return jobGroup;
	}
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}
	
	
}
