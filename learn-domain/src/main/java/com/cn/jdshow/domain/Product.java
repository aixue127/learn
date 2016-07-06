package com.cn.jdshow.domain;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 
 * @Description: 产品
 * @author mingxin.lu
 * @date 2016年6月12日 下午2:43:34   @version V1.0
 */
@Document(collection="Product")
@TypeAlias("Product")
public class Product implements Serializable {
	
	@Id
	private int id;
	@Field("prodName")
	private String prodName;
	@Field("prodCode")
	private String prodCode;
	@DBRef(db="ProductType")
	private List<ProductType> producTypeList;
	@DBRef(db="Brand")
	private Brand brand;
	
	@Version Long version;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getProdCode() {
		return prodCode;
	}
	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}
	public List<ProductType> getProducTypeList() {
		return producTypeList;
	}
	public void setProducTypeList(List<ProductType> producTypeList) {
		this.producTypeList = producTypeList;
	}
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
}
