package test.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.cn.jdshow.domain.ProductType;
import com.cn.jdshow.service.ProdAndTypeService;

import test.BaseTest;

public class ProdTest extends BaseTest{

	@Autowired
	private ProdAndTypeService prodAndTypeService;
	
	
	public void test1()throws Exception {
		ProductType pt = new ProductType();
		pt.setTypeCode("001");
		pt.setTypeName("产品类型树");
		prodAndTypeService.save(pt);
	}
	
	public void test()throws Exception {
		//ProductType prodType = prodAndTypeService.findById(ProductType.class, 7);
		//String str = JSON.toJSONString(prodType);
		ProductType pt = JSON.parseObject("{\"id\":7,\"typeCode\":\"001001333\",\"typeName\":\"娴嬭瘯000333444\"}",ProductType.class);
	}
	
	@Test
	public void test2()throws Exception {
		ProductType prodType = prodAndTypeService.findById(ProductType.class, 1);
		prodAndTypeService.update(prodType, ProductType.class);
	}
}
