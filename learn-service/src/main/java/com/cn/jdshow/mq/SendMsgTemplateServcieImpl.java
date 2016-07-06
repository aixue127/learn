package com.cn.jdshow.mq;

import org.springframework.stereotype.Service;

import com.cn.jdshow.common.activemq.MsgEnum;
import com.cn.jdshow.common.activemq.ProxyMessage;
import com.cn.jdshow.common.constant.QTMsgConstant;
import com.cn.jdshow.vo.ProdTypeInputVO;

@Service("sentMsgTemplateServcie")
public class SendMsgTemplateServcieImpl implements SendMsgTemplateServcie{

	public boolean sendprodTypeVOMessage(ProdTypeInputVO vo) {
		ProxyMessage.sendAyncMessage(QTMsgConstant.JDSHOW_QUEUE_SAVEPRODTYPE, true, MsgEnum.QUENUE, vo);
		return true;
	}
}
