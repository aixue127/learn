/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  com.cn.jdshow.vo.BaseVO
 *  com.mongodb.DBRef
 *  org.springframework.data.mongodb.core.mapping.DBRef
 *  org.springframework.data.mongodb.core.query.Criteria
 *  org.springframework.data.mongodb.core.query.CriteriaDefinition
 *  org.springframework.data.mongodb.core.query.Query
 *  org.springframework.data.mongodb.core.query.Update
 */
package com.cn.jdshow.common.mongodb;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.cn.jdshow.vo.BaseVO;

public class MongodbDMLUtil {
    
	public static <E> Query getQuery(E e) {
        Query query = new Query();
        try {
            Method m = e.getClass().getDeclaredMethod("getId", null);
            query.addCriteria(new Criteria("id").is(m.invoke(e, null)));
        }
        catch (Exception r) {
            r.printStackTrace();
        }
        return query;
    }

    public static Query getQueryByVO(BaseVO vo) {
        Query query = new Query();
        if (vo != null) {
            try {
                Method[] mms;
                Method[] arrmethod = mms = vo.getClass().getDeclaredMethods();
                int n = arrmethod.length;
                int n2 = 0;
                while (n2 < n) {
                    Method m = arrmethod[n2];
                    String pname = m.getName();
                    if (pname.substring(0, 3).equals("get") && m.getReturnType() != Void.TYPE && m.invoke(vo, null) != null) {
                        query.addCriteria(new Criteria(String.valueOf(pname.substring(3, 4).toLowerCase()) + pname.substring(4, pname.length())).is(m.invoke(vo, null)));
                    }
                    ++n2;
                }
            }
            catch (Exception r) {
                r.printStackTrace();
            }
        }
        return query;
    }

    public static Query getQueryByMap(Map<String, Object> sqlmap) {
        Query query = new Query();
        for (Map.Entry<String, Object> entry : sqlmap.entrySet()) {
            query.addCriteria(new Criteria(entry.getKey()).is(entry.getValue()));
        }
        return query;
    }

    public static <E> Update getUpdate(E e) {
        Update update = new Update();
        try {
            Method[] mms;
            Method[] arrmethod = mms = e.getClass().getDeclaredMethods();
            int n = arrmethod.length;
            int n2 = 0;
            while (n2 < n) {
                Method m = arrmethod[n2];
                String pname = m.getName();
                String fieldName = String.valueOf(pname.substring(3, 4).toLowerCase()) + pname.substring(4, pname.length());
                if (pname.substring(0, 3).equals("get") && m.getReturnType() != Void.TYPE && !"id".equals(fieldName)) {
                    Field field = e.getClass().getDeclaredField(fieldName);
                    Object fieldValue = m.invoke(e, null);
                    if (fieldValue != null) {
                        if (field.getAnnotations()[0] instanceof DBRef) {
                            if (fieldValue instanceof List) {
                                List colls = (List)fieldValue;
                                ArrayList<com.mongodb.DBRef> dbrefs = new ArrayList<com.mongodb.DBRef>();
                                int d = 0;
                                while (d < colls.size()) {
                                    com.mongodb.DBRef dbref = new com.mongodb.DBRef(colls.get(d).getClass().getSimpleName(), colls.get(d).getClass().getDeclaredMethod("getId", null).invoke(colls.get(d), null));
                                    dbrefs.add(dbref);
                                    ++d;
                                }
                                update.set(fieldName, dbrefs);
                            } else {
                                com.mongodb.DBRef dbref = new com.mongodb.DBRef(m.getReturnType().getSimpleName(), fieldValue.getClass().getDeclaredMethod("getId", null).invoke(fieldValue, null));
                                update.set(fieldName, dbref);
                            }
                        } else {
                            update.set(fieldName, fieldValue);
                        }
                    }
                }
                ++n2;
            }
        }
        catch (Exception r) {
            r.printStackTrace();
        }
        return update;
    }

    public static <T> T getIdByClass(T t) throws Exception {
        Method m = t.getClass().getDeclaredMethod("setId", Integer.TYPE);
        m.invoke(t, MongoDBSource.getIdByCollectionName(t.getClass().getSimpleName()));
        return t;
    }
}
