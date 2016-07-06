package com.cn.jdshow.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.BulkOperations;

import com.cn.jdshow.common.page.PageGrid;
import com.cn.jdshow.common.page.PageParams;
import com.cn.jdshow.vo.BaseVO;

/**
 * 
 * @Description: BaseServcie
 * @author mingxin.lu
 * @date 2016年5月9日 下午4:04:55   @version V1.0
 */
public interface BaseDao {

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
	 * @Description: 根据sqlmap获取实体数据集
	 * @param clazz
	 * @param sqlmap
	 * @return
	 * @throws Exception
	 * @date 2016年5月11日 下午2:28:23
	 */
	<E> List<E> queryListBySqlmap(Class<E> clazz, Map<String, Object> sqlmap) throws Exception;
	
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
	<E,T> void update(E e, Class<T> clazz) throws Exception;
	
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
	 * @Description: 获取批量操作
	 * @param clazz
	 * @param isORDERED
	 * @return
	 * @date 2016年5月11日 上午11:26:48
	 */
	<E> BulkOperations getBulkOperations(Class<E> clazz, boolean isORDERED);
	
	/**
	 * 
	 * @Description: 根据Map删除数据
	 * @param sqlmap
	 * @date 2016年5月17日 上午11:05:49
	 */
	<E> void deleteByMap(Map<String, Object> sqlmap, Class<E> clazz);
	
	/**
	 * 
	 * @Description: 根据父节点获取所有的子节点
	 * @param clazz
	 * @param id
	 * @return
	 * @date 2016年5月18日 上午10:36:50
	 */
	<E> List<E> findChilds(Class<E> clazz, Integer id, String pp);
	
	/**
	 * 
	 * @Description: 根据Map 获取总条数
	 * @param sqlmap
	 * @param clazz
	 * @return
	 * @throws Exception
	 * @date 2016年5月26日 下午6:57:05
	 */
	<E> Long queryCountByMap(Map sqlmap, Class<E> clazz) throws Exception;
	
	/**
	 * 
	 * @Description: 根据Map分页
	 * @param params
	 * @param clazz
	 * @param sqlmap
	 * @return
	 * @throws Exception
	 * @date 2016年5月26日 下午6:56:35
	 */
	<E> PageGrid<E> queryByNormalPageByMap(PageParams params, Class<E> clazz, Map sqlmap) throws Exception;
}
