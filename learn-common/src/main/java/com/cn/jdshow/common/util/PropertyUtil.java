package com.cn.jdshow.common.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * 
 * @Description: 读取properties配置文件
 * @author mingxin.lu
 * @date 2016年4月8日 上午11:06:05
 */
public class PropertyUtil {
	private static String quartzPath = "/property/quartz.properties";// quartz配置

	private static String projectPath = "/property/project.properties";// quartz配置
	
	public static final Properties quartzProperties = new Properties();// quartz参数使用
	public static final Properties projectProperties = new Properties();// project参数使用
	private PropertyUtil(){
		
	}
	
	static {
		InputStreamReader quartzStream = null;
		InputStreamReader projectStream = null;
		try {
			quartzStream = getInputStreamReader(quartzPath);
			projectStream = getInputStreamReader(projectPath);
			quartzProperties.load(quartzStream);
			projectProperties.load(projectStream);
		} catch (IOException e) {
			throw new ExceptionInInitializerError("不能正确读取资源文件!");
		} finally{
			try {
				if (quartzStream != null){
					quartzStream.close();
				}
				if(projectStream != null) {
					projectStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private static InputStreamReader getInputStreamReader(String path) throws UnsupportedEncodingException{
		return new InputStreamReader(PropertyUtil.class.getResourceAsStream(path),"UTF-8");
	}

	/**
	 * 
	 * @Description:  获取quartz配置文件参数
	 * @author mingxin.lu
	 * @param key
	 * @return
	 * @date 2016年4月8日 上午11:05:31
	 */
	public static String getquartzValue(String key) {
		return quartzProperties.getProperty(key);
	}

	/**
	 * 获取project的值
	 * @param key
	 * @return
     */
	public static String getProjectValue(String key) {
		return projectProperties.getProperty(key);
	}
}
