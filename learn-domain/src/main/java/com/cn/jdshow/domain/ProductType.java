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
 * @Description: 产品类型
 * @author mingxin.lu
 * @date 2016年6月12日 下午2:43:45   @version V1.0
 */
@Document(collection="ProductType")
@TypeAlias("ProductType")
public class ProductType implements Serializable{
	
	@Id
	private int id;
	@Field("typeName")
	private String typeName;
	@Field("typeCode")
	private String typeCode;
	@DBRef(db="ProductType")
	private List<ProductType> subTypeList;
	@DBRef(db="ProductType")
	private ProductType parentType;
	
	@Version Long version;
	
	public ProductType() {
		
	}
	
	public ProductType(int id) {
		this.id=id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public List<ProductType> getSubTypeList() {
		return subTypeList;
	}
	public void setSubTypeList(List<ProductType> subTypeList) {
		this.subTypeList = subTypeList;
	}
	public ProductType getParentType() {
		return parentType;
	}
	public void setParentType(ProductType parentType) {
		this.parentType = parentType;
	}
	
}
