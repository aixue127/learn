/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  com.cn.jdshow.domain.CollectionsIdx
 *  org.springframework.context.support.ClassPathXmlApplicationContext
 *  org.springframework.data.mongodb.core.FindAndModifyOptions
 *  org.springframework.data.mongodb.core.MongoTemplate
 *  org.springframework.data.mongodb.core.query.Criteria
 *  org.springframework.data.mongodb.core.query.CriteriaDefinition
 *  org.springframework.data.mongodb.core.query.Query
 *  org.springframework.data.mongodb.core.query.Update
 */
package com.cn.jdshow.common.mongodb;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.cn.jdshow.domain.CollectionsIdx;

public final class MongoDBSource {
    public static final MongoTemplate mongoTemplate;

    static {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/config/jdshow-mongodb.xml");
        mongoTemplate = (MongoTemplate)context.getBean("mongoTemplate");
    }

    private MongoDBSource() {
    }

    public static MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    public static int getIdByCollectionName(String collectionName) {
        CollectionsIdx idx = mongoTemplate.findAndModify(new Query(Criteria.where("id").is(collectionName)), new Update().inc("currIdx", 1), FindAndModifyOptions.options().upsert(true).returnNew(true), CollectionsIdx.class);
        return idx.getCurrIdx();
    }
}
