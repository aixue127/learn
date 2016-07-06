package com.cn.jdshow.mq;

import com.cn.jdshow.vo.ProdTypeInputVO;

/**
 * 
 * @Description: 消息发送模板Servcie
 * @author mingxin.lu
 * @date 2016年5月20日 下午2:40:19   @version V1.0
 */
public interface SendMsgTemplateServcie {

	boolean sendprodTypeVOMessage(ProdTypeInputVO vo);
}
