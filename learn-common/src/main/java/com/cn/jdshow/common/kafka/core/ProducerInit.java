/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  kafka.javaapi.producer.Producer
 *  kafka.producer.KeyedMessage
 *  kafka.producer.ProducerConfig
 */
package com.cn.jdshow.common.kafka.core;

import java.util.Properties;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class ProducerInit {
    public static final Producer<String, String> prod = new Producer<>(ProducerInit.getConfig());

    public static Producer<String, String> initProducer() {
        return prod;
    }

    private static ProducerConfig getConfig() {
        Properties props = new Properties();
        props.put("metadata.broker.list", "192.168.190.128:9091,192.168.190.128:9092");
        props.put("request.required.acks", "1");
        props.put("producer.type", "async");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("key.serializer.class", "kafka.serializer.StringEncoder");
        props.put("partitioner.class", "com.cn.jdshow.common.kafka.core.JdKdPartitioner");
        ProducerConfig config = new ProducerConfig(props);
        return config;
    }

    public static void sent(String topic, String key, String value) {
        KeyedMessage<String, String> msg = new KeyedMessage<>(topic, key, value);
        ProducerInit.initProducer().send(msg);
    }
}
