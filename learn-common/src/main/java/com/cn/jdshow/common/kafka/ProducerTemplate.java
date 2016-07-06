/*
 * Decompiled with CFR 0_115.
 */
package com.cn.jdshow.common.kafka;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.cn.jdshow.common.kafka.core.ProducerInit;

public class ProducerTemplate {
	
    public static final ThreadPoolExecutor kafkapooled = new ThreadPoolExecutor(10, 1000, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(500), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

    public static void asynSent(final String topname, final String key, final String data) {
        kafkapooled.execute(new Runnable(){
            @Override
            public void run() {
                ProducerInit.sent(topname, key, data);
            }
        });
    }

    public static void sent(String topname, String key, String data) {
        ProducerInit.sent(topname, key, data);
    }

}
