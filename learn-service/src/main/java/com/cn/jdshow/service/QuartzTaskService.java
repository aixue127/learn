package com.cn.jdshow.service;

import java.util.List;

import com.cn.jdshow.common.page.PageGrid;
import com.cn.jdshow.common.page.PageParams;
import com.cn.jdshow.domain.QuartzTask;

public interface QuartzTaskService extends BaseService{

	/**
	 * 
	 * @Description: 获取所有的定时任务
	 * @return
	 * @throws Exception
	 * @date 2016年5月26日 下午4:44:11
	 */
	PageGrid<QuartzTask> queryQuartzTaskPage(PageParams params) throws Exception;
}
