package com.cn.yongrui.es.enums;

public enum AggEnum {

    AGG_COUNT("aggCount","文档数据"),

    AGG_SUM("aggSum","某字段总和"),

    AGG_DISTINCT("aggDistinct","去重聚合"),

    AGG_AVG("aggAvg","查询平均值"),

    AGG_MAX("aggMax","查询最大值"),

    AGG_MIN("aggMin","查询最小值"),

    AGG_GROUP("aggGroup","按照字段分组聚合"),

    AGG_HISTOGRAM("aggHistogram","时间分组聚合"),

    AGG_STATE("aggState","基本五项信息聚合");

    private String code;

    private String describe;

    public String getCode() {
        return code;
    }

    public String getDescribe() {
        return describe;
    }

    AggEnum(String code, String name) {
        this.code = code;
        this.describe = name;
    }
}
