package com.cn.jdshow.service;

import java.util.List;

import com.cn.jdshow.model.NormalZNodes;
import com.cn.jdshow.vo.ProdTypeInputVO;

/**
 * 
 * @Description: 产品&类型service类
 * @author mingxin.lu
 * @date 2016年5月20日 下午2:33:43   @version V1.0
 */
public interface ProdAndTypeService extends BaseService{

	/**
	 * 
	 * @Description: 获取产品树
	 * @return
	 * @date 2016年5月20日 下午2:50:35
	 */
	List<NormalZNodes> queryTypeTree() throws Exception;
	
	/**
	 * 
	 * @Description: 新增产品类型
	 * @param vo
	 * @date 2016年5月20日 下午3:32:47
	 */
	void saveOrUpdType(ProdTypeInputVO vo) throws Exception;
}
