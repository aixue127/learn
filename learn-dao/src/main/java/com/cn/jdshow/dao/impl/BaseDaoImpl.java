package com.cn.jdshow.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.BulkOperations.BulkMode;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.cn.jdshow.common.mongodb.MongoDBSource;
import com.cn.jdshow.common.mongodb.MongodbDMLUtil;
import com.cn.jdshow.common.page.PageGrid;
import com.cn.jdshow.common.page.PageParams;
import com.cn.jdshow.dao.BaseDao;
import com.cn.jdshow.vo.BaseVO;
import com.mongodb.DBRef;

@Repository("baseDao")
public class BaseDaoImpl implements BaseDao{

	MongoTemplate mongoTemplate = MongoDBSource.getMongoTemplate();

	public <E> List<E> queryAll(Class<E> clazz) throws Exception {
		return mongoTemplate.findAll(clazz);
	}

	public <E> E findById(Class<E> clazz, int id) throws Exception {
		return mongoTemplate.findById(id, clazz);
	}

	public <E> void save(E e) throws Exception {
		mongoTemplate.save(MongodbDMLUtil.getIdByClass(e));
	}

	public <E,T> void update(E e, Class<T> t) throws Exception {
		mongoTemplate.updateFirst(MongodbDMLUtil.getQuery(e),MongodbDMLUtil.getUpdate(e), t);
	}

	public <E> void deleteById(Class<E> clazz, int id) throws Exception {
		mongoTemplate.remove(new Query().addCriteria(new Criteria("id").is(id)), clazz);
	}
	
	public <E> List<E> queryInfoByVO(Class<E> clazz, BaseVO vo) throws Exception {
		return mongoTemplate.find(MongodbDMLUtil.getQueryByVO(vo), clazz);
	}

	public <E> Long queryCountByVO(BaseVO vo,Class<E> clazz) throws Exception {
		return mongoTemplate.count(MongodbDMLUtil.getQueryByVO(vo), clazz);
	}
	
	public <E> Long queryCountByMap(Map sqlmap,Class<E> clazz) throws Exception {
		return mongoTemplate.count(MongodbDMLUtil.getQueryByMap(sqlmap), clazz);
	}
	
	public <E> PageGrid<E> queryByNormalPage(PageParams params, Class<E> clazz, BaseVO vo) throws Exception {
		PageGrid<E> pageGrid = new PageGrid<E>();
		pageGrid.setTotal(Integer.parseInt(this.queryCountByVO(vo, clazz).toString()));
		List<E> rows =  mongoTemplate.find(MongodbDMLUtil.getQueryByVO(vo).
				skip(params.getRowStart()).limit(params.getRowEnd()), clazz);
		pageGrid.setRows(rows);
		return pageGrid;
	}
	
	public <E> PageGrid<E> queryByNormalPageByMap(PageParams params, Class<E> clazz, Map sqlmap) throws Exception {
		PageGrid<E> pageGrid = new PageGrid<E>();
		pageGrid.setTotal(Integer.parseInt(this.queryCountByMap(sqlmap, clazz).toString()));
		List<E> rows =  mongoTemplate.find(MongodbDMLUtil.getQueryByMap(sqlmap).
				skip(params.getRowStart()).limit(params.getRowEnd()), clazz);
		pageGrid.setRows(rows);
		return pageGrid;
	}
	
	public <E> BulkOperations getBulkOperations(Class<E> clazz,boolean isORDERED) {
		BulkOperations bulks = mongoTemplate.bulkOps(isORDERED?BulkMode.ORDERED:BulkMode.UNORDERED, clazz);
		return bulks;
	}

	public <E> List<E> queryListBySqlmap(Class<E> clazz, Map<String, Object> sqlmap) throws Exception {
		return mongoTemplate.find(MongodbDMLUtil.getQueryByMap(sqlmap), clazz);
	}
	
	public <E> void deleteByMap(Map<String, Object> sqlmap, Class<E> clazz) {
		mongoTemplate.remove(MongodbDMLUtil.getQueryByMap(sqlmap), clazz);
	}
	
	public <E> List<E> findChilds(Class<E> clazz, Integer id, String pp) {
		List<E> ls = null;
		if(id == -1) { // 获取所有的子节点
			ls = mongoTemplate.find(new Query(Criteria.where(pp).exists(true)), clazz);
		} else {
			DBRef dr = new DBRef(clazz.getSimpleName(),id);
			ls = mongoTemplate.find(new Query(Criteria.where(pp).is(dr)), clazz);
		}
		return ls;
	}
}
