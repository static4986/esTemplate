package com.cn.yongrui.es.builder;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

public class EntityBuilder {

    public static BoolQueryBuilder boolQueryBuilder;

    static {
        boolQueryBuilder = QueryBuilders.boolQuery();
    }
}
