/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  org.springframework.context.ApplicationContext
 */
package com.cn.jdshow.common.kafka;

import com.cn.jdshow.common.kafka.core.ConsumerInit;
import org.springframework.context.ApplicationContext;

public class ConsumerTemplate {
    public static void start(ApplicationContext context, String groupId, String topname, Class clazz) {
        ConsumerInit t = new ConsumerInit(context, groupId, topname, clazz);
        t.init();
    }

    public static void start(ApplicationContext context, String topname, Class clazz) {
        ConsumerInit t = new ConsumerInit(context, topname, clazz);
        t.init();
    }
}
