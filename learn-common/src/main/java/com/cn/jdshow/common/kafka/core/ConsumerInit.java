/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  kafka.consumer.Consumer
 *  kafka.consumer.ConsumerConfig
 *  kafka.consumer.KafkaStream
 *  kafka.javaapi.consumer.ConsumerConnector
 *  org.springframework.context.ApplicationContext
 */
package com.cn.jdshow.common.kafka.core;

import com.cn.jdshow.common.kafka.core.ConsumerMsgTask;
import com.cn.jdshow.common.kafka.core.ExecuteBase;
import com.cn.jdshow.common.kafka.core.FactoryExecuteCallback;
import com.cn.jdshow.common.util.ThreadPoolUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import org.springframework.context.ApplicationContext;

public class ConsumerInit {
    private String groupId = "group001";
    private String topname;
    private Integer partnum = 2;
    private Class clazz;
    ApplicationContext applicationContext;
    ConsumerConnector connect;

    public ConsumerInit() {
    }

    public ConsumerInit(ApplicationContext applicationContext, String topname, Class clazz) {
        this.applicationContext = applicationContext;
        this.topname = topname;
        this.clazz = clazz;
    }

    public ConsumerInit(ApplicationContext applicationContext, String groupId, String topname, Class clazz) {
        this(applicationContext, topname, clazz);
        this.groupId = groupId;
    }

    private ConsumerConfig config() {
        Properties props = new Properties();
        props.put("zookeeper.connect", "192.168.190.128:2181");
        props.put("group.id", this.groupId);
        props.put("enable.auto.commit", "false");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "kafka.serializer.StringDecoder");
        props.put("value.deserializer", "kafka.serializer.StringDecoder");
        ConsumerConfig config = new ConsumerConfig(props);
        return config;
    }

    public void init() {
        this.connect = Consumer.createJavaConsumerConnector(this.config());
        FactoryExecuteCallback factoryExecuteCallback = (FactoryExecuteCallback)this.applicationContext.getBean("factoryExecuteCallback");
        HashMap<String, Integer> topmap = new HashMap<String, Integer>();
        topmap.put(this.topname, this.partnum);
        Map<String, List<KafkaStream<byte[], byte[]>>> tpsdatas = this.connect.createMessageStreams(topmap);
        List<KafkaStream<byte[], byte[]>> streams = tpsdatas.get(this.topname);
        for (KafkaStream<byte[], byte[]> stream : streams) {
            ThreadPoolUtil.execute(new ConsumerMsgTask(stream, factoryExecuteCallback.getBeans(clazz)));
        }
    }

    public void close(ConsumerConnector connect) {
        try {
            try {
                connect.shutdown();
            }
            catch (Exception var2_2) {
                connect.shutdown();
            }
        }
        finally {
            connect.shutdown();
        }
    }

    public ConsumerConnector getConnect() {
        return this.connect;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getTopname() {
        return this.topname;
    }

    public void setTopname(String topname) {
        this.topname = topname;
    }

    public Integer getPartnum() {
        return this.partnum;
    }

    public void setPartnum(Integer partnum) {
        this.partnum = partnum;
    }

    public ApplicationContext getContext() {
        return this.applicationContext;
    }

    public void setContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
