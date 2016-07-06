package com.cn.jdshow.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 
 * @Description: 产品图片
 * @author mingxin.lu
 * @date 2016年6月12日 下午6:20:34   @version V1.0
 */
@Document(collection="ProdPicture")
@TypeAlias(value="ProdPicture")
public class ProdPicture implements Serializable{
	@Id
	private int id;
	@Field("path")
	private String path;

}
