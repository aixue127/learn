package com.cn.jdshow.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cn.jdshow.common.constant.kafkaTopicConstant;
import com.cn.jdshow.common.fastjson.PropertyFilterForBean;
import com.cn.jdshow.common.kafka.ProducerTemplate;
import com.cn.jdshow.common.page.PageGrid;
import com.cn.jdshow.common.page.PageParams;
import com.cn.jdshow.common.util.AjaxResult;
import com.cn.jdshow.common.util.SpringMVCUtil;
import com.cn.jdshow.domain.Product;
import com.cn.jdshow.domain.ProductType;
import com.cn.jdshow.domain.User;
import com.cn.jdshow.model.NormalZNodes;
import com.cn.jdshow.mq.SendMsgTemplateServcie;
import com.cn.jdshow.service.ProdAndTypeService;
import com.cn.jdshow.vo.ProdTypeInputVO;

@Controller
@RequestMapping("/jdshow/prodAndType")
public class ProdAndTypeController {

	@Autowired
	private ProdAndTypeService prodAndTypeService;
	
	@Autowired
	private SendMsgTemplateServcie sendMsgTemplateServcie;
	
	/**
	 * 
	 * @Description: 显示所有的产品类型，，树形结构
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @date 2016年5月20日 下午2:43:39
	 */
	@RequestMapping("/goQueryProdType")
	public String goQueryProdType(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		return "/admin/prodTypeQuery";
	}
	
	/**
	 * 
	 * @Description: 树形结构
	 * @param request
	 * @param response
	 * @date 2016年5月20日 下午3:23:15
	 */
	@RequestMapping("/queryTypeTree")
	@ResponseBody
	public void queryTypeTree(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<NormalZNodes> nzs = prodAndTypeService.queryTypeTree();
			SpringMVCUtil.printWriter(response, JSON.toJSONString(nzs, new PropertyFilterForBean(new String[]{"iconSkin","isParent"})));
		} catch(Exception e) {
			e.printStackTrace();
			SpringMVCUtil.printWriter(response, JSON.toJSONString(AjaxResult.failResult("error")));
		}
	}
	
	/**
	 * 
	 * @Description: 保存或修改TypeTree
	 * @date 2016年5月20日 下午3:26:29
	 */
	@RequestMapping("/saveOrUpdType")
	@ResponseBody
	public void saveOrUpdType(HttpServletRequest request, HttpServletResponse response, ProdTypeInputVO vo) {
		try {
			sendMsgTemplateServcie.sendprodTypeVOMessage(vo);
			SpringMVCUtil.printWriter(response, JSON.toJSONString(AjaxResult.successResult("success")));
		} catch(Exception e) {
			e.printStackTrace();
			SpringMVCUtil.printWriter(response, JSON.toJSONString(AjaxResult.failResult("error")));
		}
	}
	
	/**
	 * 
	 * @Description: 删除操作
	 * @param request
	 * @param response
	 * @param id
	 * @date 2016年5月23日 下午2:34:41
	 */
	@RequestMapping("/deleteType")
	@ResponseBody
	public void deleteType(HttpServletRequest request, HttpServletResponse response, @RequestParam("id")String id) {
		// 获取对象
		try {
			ProductType prodType = prodAndTypeService.findById(ProductType.class, Integer.parseInt(id));
			ProducerTemplate.asynSent(kafkaTopicConstant.JDSHOW_KAFKA_PRODTYPDEL, 
					kafkaTopicConstant.JDSHOW_KAFKA_PRODTYPDEL+"_"+id, JSON.toJSONString(prodType, new PropertyFilterForBean(new String[]{"subTypeList","parentType"}) ));
			SpringMVCUtil.printWriter(response, JSON.toJSONString(AjaxResult.successResult("success")));
		} catch(Exception e) {
			e.printStackTrace();
			SpringMVCUtil.printWriter(response, JSON.toJSONString(AjaxResult.failResult("error")));
		}
	}
	
	/**
	 * 
	 * @Description: 产品页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @date 2016年6月12日 下午3:01:44
	 */
	@RequestMapping("/goQueryProduct")
	public String goQueryProduct(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		return "/admin/productQuery";
	}
	
	@RequestMapping("/queryProduct")
	@ResponseBody
	public void queryProduct(PageParams params, HttpServletRequest request, HttpServletResponse response) {
		try {
			PageGrid<Product> pageGrid = prodAndTypeService.queryByNormalPage(params, Product.class, null);
			SpringMVCUtil.printWriter(response,  JSON.toJSONString(pageGrid));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
