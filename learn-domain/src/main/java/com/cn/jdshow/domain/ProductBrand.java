package com.cn.jdshow.domain;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @Description: 产品品牌
 * @author mingxin.lu
 * @date 2016年6月12日 下午2:47:47   @version V1.0
 */
@Document(collection="ProductBrand")
@TypeAlias("ProductBrand")
public class ProductBrand implements Serializable{
	@Id
	private int id;
	@DBRef(db="Brand")
	private Brand brand;
	@DBRef(db="Product")
	private List<Product> subProds;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public List<Product> getSubProds() {
		return subProds;
	}
	public void setSubProds(List<Product> subProds) {
		this.subProds = subProds;
	}
	
}
