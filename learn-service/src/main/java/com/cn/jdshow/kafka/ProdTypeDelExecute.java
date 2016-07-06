package com.cn.jdshow.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.cn.jdshow.common.kafka.core.ExecuteBase;
import com.cn.jdshow.domain.ProductType;
import com.cn.jdshow.service.ProdAndTypeService;

@Component("prodTypeDelExecute")
public class ProdTypeDelExecute extends ExecuteBase{

	@Autowired
	private ProdAndTypeService prodAndTypeService;

	@Override
	public void execute(String data) {
		try {
			ProductType type = JSON.parseObject(data, ProductType.class);
			prodAndTypeService.deleteById(ProductType.class, type.getId());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
