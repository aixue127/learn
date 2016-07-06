package com.cn.jdshow.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cn.jdshow.common.page.PageParams;
import com.cn.jdshow.common.quartz.ISchedulerInfoTemplate;
import com.cn.jdshow.common.util.AjaxResult;
import com.cn.jdshow.common.util.SpringMVCUtil;
import com.cn.jdshow.domain.QuartzTask;
import com.cn.jdshow.service.QuartzTaskService;

@Controller
@RequestMapping("/jdshow/quartzTask")
public class QuartzTaskController {

	@Autowired
	private QuartzTaskService quartzTaskService;
	
	@Autowired
	private ISchedulerInfoTemplate schedulerInfoTemplate;
	
	@RequestMapping("/goQueryQuartzTask")
	public String goQuartzTask(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		return "/admin/quartzTaskQuery";
	}
	
	@RequestMapping("queryQuartzTask")
	@ResponseBody
	public void queryQuartzTask(PageParams params, HttpServletRequest request, HttpServletResponse response) {
		try {
			SpringMVCUtil.printWriter(response, JSON.toJSONString(quartzTaskService.queryQuartzTaskPage(params)));
		} catch(Exception e) {
			e.printStackTrace();
			SpringMVCUtil.printWriter(response, JSON.toJSONString(AjaxResult.failResult("faild")));
		}
		
	}
	
	@RequestMapping("/insOrUpdTask")
	@ResponseBody
	public void insOrUpdTask(QuartzTask vo, HttpServletRequest request, HttpServletResponse response) {
		try {
			if(vo.getId() > 0) {
				quartzTaskService.update(vo, QuartzTask.class);
			} else {
				quartzTaskService.save(vo);
			}
			SpringMVCUtil.printWriter(response, JSON.toJSONString(AjaxResult.successResult("success")));
		} catch(Exception e) {
			e.printStackTrace();
			SpringMVCUtil.printWriter(response, JSON.toJSONString(AjaxResult.failResult("faild")));
		}
	}
	
	@RequestMapping("/deleteTask")
	@ResponseBody
	public void deleteTask(HttpServletRequest request, HttpServletResponse response,@RequestParam("id") String id) {
		try{
			if(StringUtils.isNotBlank(id)) {
				// 删除定时任务
				schedulerInfoTemplate.sotpTask(quartzTaskService.findById(QuartzTask.class, Integer.parseInt(id)));
				quartzTaskService.deleteById(QuartzTask.class, Integer.parseInt(id));
				SpringMVCUtil.printWriter(response, JSON.toJSONString(AjaxResult.successResult("success")));
			}
		} catch(Exception e) {
			e.printStackTrace();
			SpringMVCUtil.printWriter(response, JSON.toJSONString(AjaxResult.failResult("fail")));
		}
	}
	
	@RequestMapping("/startOrStopTask")
	@ResponseBody
	public void startOrStopTask(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("id") String id, @RequestParam("operator") String operator) {
		try {
			QuartzTask task = quartzTaskService.findById(QuartzTask.class, Integer.parseInt(id));
			if("1".equals(operator)) {
				schedulerInfoTemplate.addTask(task);
			} else {
				schedulerInfoTemplate.sotpTask(task);
			}
			SpringMVCUtil.printWriter(response, JSON.toJSONString(AjaxResult.successResult("success")));
		} catch(Exception e) {
			e.printStackTrace();
			SpringMVCUtil.printWriter(response, JSON.toJSONString(AjaxResult.failResult("fail")));
		}
		
	}
}
