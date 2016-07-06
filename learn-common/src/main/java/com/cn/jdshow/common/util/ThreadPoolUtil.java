/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
 */
package com.cn.jdshow.common.util;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class ThreadPoolUtil {
    private static ThreadPoolTaskExecutor pooled = new ThreadPoolTaskExecutor();

    static {
        pooled.setQueueCapacity(500);
        pooled.setCorePoolSize(10);
        pooled.setMaxPoolSize(1000);
        pooled.setKeepAliveSeconds(60);
        pooled.initialize();
    }

    public static void execute(Thread thread) {
        pooled.execute(thread);
    }

    public static void execute(Runnable runnable) {
        pooled.execute(runnable);
    }
}
