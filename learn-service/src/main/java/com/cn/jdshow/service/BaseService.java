package com.cn.jdshow.service;

import java.util.List;

import com.cn.jdshow.common.page.PageGrid;
import com.cn.jdshow.common.page.PageParams;
import com.cn.jdshow.vo.BaseVO;

public interface BaseService {

	/**
	 * 
	 * @Description: 查询全部数据
	 * @param clazz
	 * @return
	 * @throws Exception
	 * @date 2016年5月9日 下午4:05:17
	 */
	<E> List<E> queryAll(Class<E> clazz) throws Exception;
	
	/**
	 * 
	 * @Description: 根据Id获取实体数据
	 * @param clazz
	 * @param id
	 * @return
	 * @throws Exception
	 * @date 2016年5月9日 下午4:06:34
	 */
	<E> E findById(Class<E> clazz, int id) throws Exception;
	
	/**
	 * 
	 * @Description: 保存数据
	 * @param e
	 * @throws Exception
	 * @date 2016年5月9日 下午4:06:48
	 */
	<E> void save(E e) throws Exception;
	
	/**
	 * 
	 * @Description: 更新数据
	 * @param e 实体
	 * @param t 对应class
	 * @throws Exception
	 * @date 2016年5月9日 下午4:06:55
	 */
	<E,T> void update(E e, Class<T> t) throws Exception;
	
	/**
	 * 
	 * @Description: 更具Id删除实体数据
	 * @param clazz
	 * @param id
	 * @throws Exception
	 * @date 2016年5月9日 下午4:07:19
	 */
	<E> void deleteById(Class<E> clazz, int id) throws Exception;
	
	/**
	 * 
	 * @Description: 根据VO获取实体数据
	 * @param clazz
	 * @param vo
	 * @return
	 * @throws Exception
	 * @date 2016年5月9日 下午4:07:36
	 */
	<E> List<E> queryInfoByVO(Class<E> clazz, BaseVO vo) throws Exception;
	
	/**
	 * 
	 * @Description: 获取count数量
	 * @param vo
	 * @param clazz
	 * @return
	 * @throws Exception
	 * @date 2016年5月9日 下午4:17:05
	 */
	<E> Long queryCountByVO(BaseVO vo, Class<E> clazz) throws Exception;
	
	/**
	 * 
	 * @Description: 普通分页查询
	 * @param params 分页入参
	 * @param clazz 分页实体对象
	 * @param vo 查询条件
	 * @return
	 * @date 2016年5月9日 下午4:09:24
	 */
	<E> PageGrid<E> queryByNormalPage(PageParams params, Class<E> clazz, BaseVO vo) throws Exception;
	
	/**
	 * 
	 * @Description: 根据父Id获取子节点为-1时获取所有的子节点
	 * @param clazz
	 * @param id
	 * @param pp
	 * @return
	 * @date 2016年5月18日 上午10:46:53
	 */
	<E> List<E> findChilds(Class<E> clazz, Integer id, String pp);
	
}
