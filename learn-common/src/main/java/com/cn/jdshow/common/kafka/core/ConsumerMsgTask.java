/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  kafka.consumer.KafkaStream
 *  kafka.message.MessageAndMetadata
 *  org.apache.log4j.Logger
 */
package com.cn.jdshow.common.kafka.core;

import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;

import kafka.consumer.KafkaStream;
import kafka.message.MessageAndMetadata;

public class ConsumerMsgTask implements Runnable {
	
    Logger logger = Logger.getLogger(ConsumerMsgTask.class);
    
    KafkaStream<byte[], byte[]> kafkaStream;
    ExecuteBase cb;

    public ConsumerMsgTask(KafkaStream<byte[], byte[]> kafkaStream, ExecuteBase cb) {
        this.kafkaStream = kafkaStream;
        this.cb = cb;
    }

    @Override
    public void run() {
        for (MessageAndMetadata<byte[], byte[]> mesg : this.kafkaStream) {
            String message = "";
            try {
                message = new String(mesg.message(), "UTF-8");
            }
            catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            this.logger.info("===========kafka接收Key【" + new String(mesg.key()) + "】消息内容【" + message + "】=============");
            this.cb.execute(message);
        }
    }
}
