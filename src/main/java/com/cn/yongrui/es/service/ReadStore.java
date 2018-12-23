package com.cn.yongrui.es.service;

import com.cn.yongrui.es.entity.Writeable;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.List;
import java.util.Map;

public interface ReadStore{

    List<? extends Writeable> readEs(SearchQuery searchQuery, Writeable writeable);

    Long queryCount(SearchQuery searchQuery, Writeable writeable);

    Double querySum(SearchQuery searchQuery);

    Double queryMax(SearchQuery searchQuery);

    Double queryAvg(SearchQuery searchQuery);

    Double queryGroup(SearchQuery searchQuery);

    Map<String,Object> queryState(SearchQuery searchQuery);

}
