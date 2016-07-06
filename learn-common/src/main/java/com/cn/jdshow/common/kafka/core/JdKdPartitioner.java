/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  kafka.producer.Partitioner
 *  kafka.utils.VerifiableProperties
 */
package com.cn.jdshow.common.kafka.core;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

public class JdKdPartitioner implements Partitioner {
    public JdKdPartitioner(VerifiableProperties props) {
    }

    public int partition(Object arg0, int arg1) {
        String id = arg0.toString().split("_")[1];
        return Integer.parseInt(id) % arg1;
    }
}
