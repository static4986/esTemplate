package com.cn.yongrui.es;

import org.slf4j.MDC;

public class test {
    public static void main(String[] args) {
        String traceId = MDC.get("X-B3-TraceId");
        System.out.println(traceId);
    }
}
