package com.cn.jdshow.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.jdshow.dao.ProdAndTypeDao;
import com.cn.jdshow.domain.ProductType;
import com.cn.jdshow.model.NormalZNodes;
import com.cn.jdshow.service.ProdAndTypeService;
import com.cn.jdshow.vo.ProdTypeInputVO;

@Service("prodAndTypeService")
public class ProdAndTypeServiceImpl extends BaseServiceImpl implements ProdAndTypeService{

	@Autowired
	private ProdAndTypeDao prodAndTypeDao;
	
	public List<NormalZNodes> queryTypeTree() throws Exception {
		List<NormalZNodes> nzs = new ArrayList<>();
		List<ProductType> ptlist = prodAndTypeDao.queryAll(ProductType.class);
		for(ProductType pt : ptlist) {
			NormalZNodes nz = new NormalZNodes();
			nz.setId(pt.getId());
			nz.setName(pt.getTypeName());
			nz.setTreeCode(pt.getTypeCode());
			nz.setpId(pt.getParentType() != null?pt.getParentType().getId():0);
			nzs.add(nz);
		}
		return nzs;
	}
	
	public void saveOrUpdType(ProdTypeInputVO vo) throws Exception {
		if(StringUtils.isNotBlank(vo.getId())) { // 修改
			ProductType pt = prodAndTypeDao.findById(ProductType.class, Integer.parseInt(vo.getId()));
			pt.setTypeCode(vo.getTypeCode());
			pt.setTypeName(vo.getTypeName());
			pt.setParentType(null);
			pt.setSubTypeList(null);
			prodAndTypeDao.update(pt, ProductType.class);
		} else {
			ProductType pt = new ProductType();
			pt.setTypeCode(vo.getTypeCode());
			pt.setTypeName(vo.getTypeName());
			if(StringUtils.isNotBlank(vo.getParentId())) {
				// 获取父节点
				ProductType ppt = prodAndTypeDao.findById(ProductType.class, Integer.parseInt(vo.getParentId()));
				pt.setParentType(ppt);
				prodAndTypeDao.save(pt);
				// 同时需要更新父节点数据
				if(ppt.getSubTypeList() == null) {
					List<ProductType> subTypeList = new ArrayList<>();
					subTypeList.add(pt);
					ppt.setSubTypeList(subTypeList);
				} else {
					ppt.getSubTypeList().add(pt);
				}
				prodAndTypeDao.update(ppt, ProductType.class);
			}
		}
	}
}
