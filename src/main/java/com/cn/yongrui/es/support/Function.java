package com.cn.yongrui.es.support;


@FunctionalInterface
public interface Function<T,U> {

    U apply(T t);
}
