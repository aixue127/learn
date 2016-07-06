/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  com.alibaba.fastjson.JSON
 *  javax.jms.ConnectionFactory
 *  javax.jms.Destination
 *  org.apache.activemq.command.ActiveMQQueue
 *  org.apache.activemq.command.ActiveMQTopic
 *  org.apache.activemq.jms.pool.PooledConnectionFactory
 *  org.apache.activemq.spring.ActiveMQConnectionFactory
 *  org.apache.log4j.Logger
 *  org.springframework.jms.core.JmsTemplate
 */
package com.cn.jdshow.common.activemq;

import com.alibaba.fastjson.JSON;
import com.cn.jdshow.common.activemq.MsgEnum;
import com.cn.jdshow.common.util.ThreadPoolUtil;
import java.util.concurrent.ConcurrentHashMap;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;

public class ProxyMessage {
    protected static final Logger logger = Logger.getLogger(ProxyMessage.class);
    public static final ConcurrentHashMap<String, JmsTemplate> templateMap = new ConcurrentHashMap<>();

    public static PooledConnectionFactory initPoolFactory() {
        PooledConnectionFactory pooled = new PooledConnectionFactory();
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL("tcp://192.168.190.128:61616");
        connectionFactory.setUseAsyncSend(true);
        pooled.setIdleTimeout(20000);
        pooled.setConnectionFactory(connectionFactory);
        pooled.setMaxConnections(2);
        return pooled;
    }

    public static JmsTemplate initJmsTemplate(String topicname, boolean deliveryMode, MsgEnum en) {
        JmsTemplate template = templateMap.get(topicname);
        if (template == null) {
            template = new JmsTemplate();
            template.setConnectionFactory(ProxyMessage.initPoolFactory());
            if (en == MsgEnum.QUENUE) {
                template.setDefaultDestination(new ActiveMQQueue(topicname));
            } else {
                template.setDefaultDestination(new ActiveMQTopic(topicname));
                template.setPubSubDomain(true);
            }
            template.setExplicitQosEnabled(true);
            if (deliveryMode) {
                template.setDeliveryMode(2);
            } else {
                template.setDeliveryMode(1);
            }
            templateMap.put(topicname, template);
        }
        return template;
    }

    public static boolean sendMessage(String topicname, boolean deliveryMode, MsgEnum en, Object datas) {
        boolean result = true;
        try {
            String jsondata = JSON.toJSONString(datas);
            ProxyMessage.initJmsTemplate(topicname, deliveryMode, en).convertAndSend(jsondata);
            logger.info("发送mq:消息== [队列]:" + topicname + " - [内容] :" + jsondata);
        }
        catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public static void sendAyncMessage(final String topicname, final boolean deliveryMode, final MsgEnum en, final Object datas) {
        ThreadPoolUtil.execute(new Runnable(){
            @Override
            public void run() {
                ProxyMessage.sendMessage(topicname, deliveryMode, en, datas);
            }
        });
    }

}
