package com.cn.yongrui.es.service;

import com.cn.yongrui.es.entity.Writeable;
import org.springframework.data.elasticsearch.core.query.IndexQuery;

import java.util.List;

public interface WriteStore {

    void writeEs(IndexQuery indexQuery);

    void writeEsBulk(List<IndexQuery> indexQueryList);

}
