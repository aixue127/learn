/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  org.springframework.beans.BeansException
 *  org.springframework.context.ApplicationContext
 *  org.springframework.context.ApplicationContextAware
 *  org.springframework.stereotype.Component
 */
package com.cn.jdshow.common.kafka.core;

import com.cn.jdshow.common.kafka.core.ExecuteBase;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component(value="factoryExecuteCallback")
public class FactoryExecuteCallback implements ApplicationContextAware {
    private ApplicationContext context;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public <T extends ExecuteBase> T getBeans(Class<T> t) {
        return context.getBean(t);
    }
}
