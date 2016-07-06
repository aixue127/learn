package com.cn.jdshow.quartz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cn.jdshow.common.quartz.ITaskExecute;
import com.cn.jdshow.domain.ProductType;
import com.cn.jdshow.service.ProdAndTypeService;

@Component("prodTypeRefTask")
public class ProdTypeRefTask implements ITaskExecute{

	@Autowired
	private ProdAndTypeService prodAndTypeService;

	@Override
	public void doit() {
		try {
			// 获取所有的数据
			List<ProductType> list = prodAndTypeService.queryAll(ProductType.class);
			for(ProductType pt : list) {
				List<ProductType> subList = prodAndTypeService.findChilds(ProductType.class, pt.getId(), "parentType");
				if(subList != null) {
					pt.setSubTypeList(subList);
				}
				prodAndTypeService.update(pt, ProductType.class);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
