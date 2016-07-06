package com.cn.jdshow.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.jdshow.common.page.PageGrid;
import com.cn.jdshow.common.page.PageParams;
import com.cn.jdshow.dao.BaseDao;
import com.cn.jdshow.service.BaseService;
import com.cn.jdshow.vo.BaseVO;

@Service("baseService")
public class BaseServiceImpl implements BaseService{

	@Autowired
	private BaseDao baseDao;
	
	public <E> List<E> queryAll(Class<E> clazz) throws Exception {
		return baseDao.queryAll(clazz);
	}

	public <E> E findById(Class<E> clazz, int id) throws Exception {
		return baseDao.findById(clazz, id);
	}

	public <E> void save(E e) throws Exception {
		baseDao.save(e);
	}

	public <E, T> void update(E e, Class<T> t) throws Exception {
		baseDao.update(e, t);
	}

	public <E> void deleteById(Class<E> clazz, int id) throws Exception {
		baseDao.deleteById(clazz, id);
	}

	public <E> List<E> queryInfoByVO(Class<E> clazz, BaseVO vo) throws Exception {
		return baseDao.queryInfoByVO(clazz, vo);
	}

	public <E> Long queryCountByVO(BaseVO vo, Class<E> clazz) throws Exception {
		return baseDao.queryCountByVO(vo, clazz);
	}

	public <E> PageGrid<E> queryByNormalPage(PageParams params, Class<E> clazz, BaseVO vo) throws Exception {
		return baseDao.queryByNormalPage(params, clazz, vo);
	}

	public <E> List<E> findChilds(Class<E> clazz, Integer id, String pp) {
		return baseDao.findChilds(clazz, id, pp);
	}
}
