package com.cn.jdshow.mq.listener;

import javax.jms.Message;

import org.springframework.beans.factory.annotation.Autowired;

import com.cn.jdshow.common.activemq.BaseListener;
import com.cn.jdshow.service.ProdAndTypeService;
import com.cn.jdshow.vo.ProdTypeInputVO;

public class SaveProdTypeListener extends BaseListener{

	@Autowired
	private ProdAndTypeService prodAndTypeService;
	
	@Override
	public void onMessage(Message message) {
		ProdTypeInputVO vo = this.convertMessage(message, ProdTypeInputVO.class);
		try {
			prodAndTypeService.saveOrUpdType(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
