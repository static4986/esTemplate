package com.cn.yongrui.es.builder;

import com.cn.yongrui.es.entity.Student;
import com.cn.yongrui.es.enums.AggEnum;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.filter.FilterAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.avg.AvgAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.cardinality.CardinalityAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.max.MaxAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.stats.StatsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.sum.SumAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.valuecount.ValueCountAggregationBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.util.Date;

public class StudentQueryBuilder extends EntityBuilder {

    public NativeSearchQuery nativeSearchQuery;

    public StudentQueryBuilder(Student student) {
        nativeSearchQuery = buildeAll(student);
    }

    public NativeSearchQuery buildeAll(Student student) {
        setAge(student.getAge());
        setBirthday(student.getBirthday());
        setGrade(student.getGrade());
        setName(student.getName());
        setSid(student.getSid());
        NativeSearchQuery nativeSearchQuery = buildQuery(boolQueryBuilder);
        return nativeSearchQuery;
    }

    public StudentQueryBuilder setSid(String sid) {
        if (null != sid)
            boolQueryBuilder = QueryBuilders.boolQuery().filter(QueryBuilders.termQuery("sid.keyword", sid));
        return this;
    }

    public StudentQueryBuilder setName(String name) {
        if (null != name)
            boolQueryBuilder = QueryBuilders.boolQuery().filter(QueryBuilders.termQuery("name.keyword", name));
        return this;
    }

    public StudentQueryBuilder setAge(Integer age) {
        if (null != age)
            boolQueryBuilder = QueryBuilders.boolQuery().filter(QueryBuilders.termQuery("age.keyword", age));
        return this;
    }

    public StudentQueryBuilder setGrade(String grade) {
        if (null != grade)
            boolQueryBuilder = QueryBuilders.boolQuery().filter(QueryBuilders.termQuery("grade.keyword", grade));
        return this;
    }

    public StudentQueryBuilder setBirthday(Date birthday) {
        if (null != birthday)
            boolQueryBuilder = QueryBuilders.boolQuery().filter(QueryBuilders.termQuery("birthday.keyword", birthday));
        return this;
    }

    public NativeSearchQuery buildQuery(QueryBuilder queryBuilder) {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .build();
        return nativeSearchQuery;
    }

    public NativeSearchQuery countAggBuilder(NativeSearchQuery nativeSearchQuery) {

        ValueCountAggregationBuilder countAgg = AggregationBuilders.count(AggEnum.AGG_COUNT.getCode());
        nativeSearchQuery.addAggregation(countAgg);
        return nativeSearchQuery;
    }

    public NativeSearchQuery sumAggBuilder(NativeSearchQuery nativeSearchQuery, String field) {
        SumAggregationBuilder sumAggregationBuilder = AggregationBuilders.sum(AggEnum.AGG_SUM.getCode()).field(field);
        nativeSearchQuery.addAggregation(sumAggregationBuilder);
        return nativeSearchQuery;
    }

    //去重聚合
    public NativeSearchQuery cardinalityBuilder(NativeSearchQuery nativeSearchQuery, String field) {

        CardinalityAggregationBuilder card = AggregationBuilders.cardinality(AggEnum.AGG_DISTINCT.getCode()).field(field);
        nativeSearchQuery.addAggregation(card);
        return nativeSearchQuery;
    }

    //词项聚合
    public NativeSearchQuery filterAgg(NativeSearchQuery nativeSearchQuery,String field) {

        TermsAggregationBuilder terms = AggregationBuilders.terms(AggEnum.AGG_GROUP.getCode()).field(field);
        nativeSearchQuery.addAggregation(terms);
        return nativeSearchQuery;

    }

    //平均值聚合
    public NativeSearchQuery avgAgg(NativeSearchQuery nativeSearchQuery,String field){
        AvgAggregationBuilder avgAgg = AggregationBuilders.avg(AggEnum.AGG_AVG.getCode()).field(field);
        nativeSearchQuery.addAggregation(avgAgg);
        return nativeSearchQuery;
    }

    //最大值聚合
    public NativeSearchQuery maxAgg(NativeSearchQuery nativeSearchQuery,String field){
        MaxAggregationBuilder maxAgg = AggregationBuilders.max(AggEnum.AGG_MAX.getCode()).field(field);
        nativeSearchQuery.addAggregation(maxAgg);
        return nativeSearchQuery;

    }


    //按日期间隔分组
    public NativeSearchQuery histogramAgg(NativeSearchQuery nativeSearchQuery,String field){
        DateHistogramAggregationBuilder dateHisAgg = AggregationBuilders.dateHistogram(AggEnum.AGG_HISTOGRAM.getCode())
                                                                        .field(field);
        nativeSearchQuery.addAggregation(dateHisAgg);
        return nativeSearchQuery;
    }

    //多项指标聚合
    public NativeSearchQuery stateAgg(NativeSearchQuery nativeSearchQuery,String field){
        StatsAggregationBuilder stateAgg = AggregationBuilders.stats(AggEnum.AGG_STATE.getCode()).field(field);
        nativeSearchQuery.addAggregation(stateAgg);
        return nativeSearchQuery;
    }



    //获取聚合里面的结果 AggregationBuilders.topHits("top_result");
    //嵌套的聚合 AggregationBuilders.nested("negsted_path").path("quests");
    //AggregationBuilders.reverseNested("res_negsted").path("kps ");




}
