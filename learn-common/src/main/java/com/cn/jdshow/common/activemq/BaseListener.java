/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  com.alibaba.fastjson.JSON
 *  javax.jms.Message
 *  javax.jms.MessageListener
 *  javax.jms.TextMessage
 *  org.apache.log4j.Logger
 */
package com.cn.jdshow.common.activemq;

import com.alibaba.fastjson.JSON;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import org.apache.log4j.Logger;

public abstract class BaseListener implements MessageListener {
    protected static final Logger logger = Logger.getLogger(BaseListener.class);

    public abstract void onMessage(Message var1);

    public String convertMessage(Message message) {
        return this.getMessageContent(message);
    }

    public <E> E convertMessage(Message message, Class<E> clazz) {
        String content = this.getMessageContent(message);
        E e = null;
        if (content != null) {
            e = JSON.parseObject(content, clazz);
        }
        return e;
    }

    public String getMessageContent(Message message) {
        String data = null;
        if (message instanceof TextMessage) {
            try {
                data = ((TextMessage)message).getText();
                logger.info("接收mq:消息内容] :" + data);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return data;
    }
}
