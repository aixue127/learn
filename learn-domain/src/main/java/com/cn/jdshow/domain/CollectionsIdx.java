package com.cn.jdshow.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 
 * @Description: IDs管理
 * @author mingxin.lu
 * @date 2016年6月12日 下午2:43:22   @version V1.0
 */
@Document(collection="CollectionsIdx")
@TypeAlias("CollectionsIdx")
public class CollectionsIdx {

	@Id
	private String id;
	@Field("currIdx")
	private int currIdx;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getCurrIdx() {
		return currIdx;
	}
	public void setCurrIdx(int currIdx) {
		this.currIdx = currIdx;
	}
	
}
