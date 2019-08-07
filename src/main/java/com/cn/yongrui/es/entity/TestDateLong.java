package com.cn.yongrui.es.entity;

import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

@Document(indexName="testdatelong",type="book")
public class TestDateLong {

    private String name;

    private Date public_time;

    private long public_time_timestamp;

    private long price;

    public TestDateLong(String name, Date public_time, long public_time_timestamp, long price) {
        this.name = name;
        this.public_time = public_time;
        this.public_time_timestamp = public_time_timestamp;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getPublic_time() {
        return public_time;
    }

    public void setPublic_time(Date public_time) {
        this.public_time = public_time;
    }

    public long getPublic_time_timestamp() {
        return public_time_timestamp;
    }

    public void setPublic_time_timestamp(long public_time_timestamp) {
        this.public_time_timestamp = public_time_timestamp;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "TestDateLong{" +
                "name='" + name + '\'' +
                ", public_time=" + public_time +
                ", public_time_timestamp=" + public_time_timestamp +
                ", price=" + price +
                '}';
    }
}
