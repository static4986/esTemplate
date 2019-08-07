package com.cn.yongrui.es.service.Impl.forkService;

import com.cn.yongrui.es.support.ForkPojo;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class ForkPojoObj implements ForkPojo {

    public ForkPojoObj() {
    }

    public ForkPojoObj(LocalDateTime begin, LocalDateTime end) {
        this.begin = begin;
        this.end = end;
    }

    public ForkPojoObj(long begin, long end) {
        this.begin = Instant.ofEpochMilli(begin).atZone(ZoneId.systemDefault()).toLocalDateTime();
        this.end = Instant.ofEpochMilli(end).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    private LocalDateTime begin;

    private LocalDateTime end;

    public LocalDateTime getBegin() {
        return begin;
    }

    public void setBegin(LocalDateTime begin) {
        this.begin = begin;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
    public void setEnd(long end) {
        this.end = Instant.ofEpochMilli(end).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

}
