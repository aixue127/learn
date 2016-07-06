package com.cn.jdshow.service.impl;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.jdshow.common.page.PageGrid;
import com.cn.jdshow.common.page.PageParams;
import com.cn.jdshow.common.quartz.ISchedulerInfoTemplate;
import com.cn.jdshow.dao.QuartzTaskDao;
import com.cn.jdshow.domain.QuartzTask;
import com.cn.jdshow.service.QuartzTaskService;

@Service("quartzTaskService")
public class QuartzTaskServiceImpl extends BaseServiceImpl implements QuartzTaskService{

	@Autowired
	private ISchedulerInfoTemplate schedulerInfoTemplate;
	
	@Autowired
	private QuartzTaskDao quartzTaskDao;
	
	public PageGrid<QuartzTask> queryQuartzTaskPage(PageParams params) throws Exception {
		Map<String, Object> sqlmap = new HashMap<>();
		sqlmap.put("ip", InetAddress.getLocalHost().getHostAddress());
		PageGrid<QuartzTask> pages = quartzTaskDao.queryByNormalPageByMap(params, QuartzTask.class, sqlmap);
		if(pages.getRows() != null) {
			// 获取启动的定时任务
			List<String> alyed = schedulerInfoTemplate.getEnableTaskName();
			for(QuartzTask qt : pages.getRows()) {
				boolean isenable = false;
				for(String s : alyed) {
					if(qt.getJobName().equals(s)) {
						qt.setStatus("1");
						isenable = true;
						break;
					}
				}
				if(!isenable) {
					qt.setStatus("0");
				}
			}
		}
		return pages;
	}

}
