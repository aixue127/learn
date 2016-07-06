package com.cn.jdshow.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


import java.io.Serializable;

/**
 * 
 * @Description: 品牌
 * @author mingxin.lu
 * @date 2016年6月12日 下午2:42:43   @version V1.0
 */
@Document(collection="Brand")
@TypeAlias(value="Brand")
public class Brand implements Serializable{

	@Id
	private int id;
	@Field("braCode")
	private String braCode;
	@Field("braName")
	private String braName;
	@DBRef(db="Brand")
	@Field("parBrand")
	private Brand parBrand;
	@Field("bigPath")
	private String bigPath;
	@Field("smallPath")
	private String smallPath;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBraCode() {
		return braCode;
	}

	public void setBraCode(String braCode) {
		this.braCode = braCode;
	}

	public String getBraName() {
		return braName;
	}

	public void setBraName(String braName) {
		this.braName = braName;
	}

	public Brand getParBrand() {
		return parBrand;
	}

	public void setParBrand(Brand parBrand) {
		this.parBrand = parBrand;
	}

	public String getBigPath() {
		return bigPath;
	}

	public void setBigPath(String bigPath) {
		this.bigPath = bigPath;
	}

	public String getSmallPath() {
		return smallPath;
	}

	public void setSmallPath(String smallPath) {
		this.smallPath = smallPath;
	}
}
