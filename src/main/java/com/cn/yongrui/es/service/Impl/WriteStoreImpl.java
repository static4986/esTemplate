package com.cn.yongrui.es.service.Impl;

import com.cn.yongrui.es.service.WriteStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WriteStoreImpl implements WriteStore {

    @Autowired(required = false)
    private ElasticsearchTemplate template;

    @Override
    public void writeEs(IndexQuery indexQuery) {

        template.index(indexQuery);

    }

    @Override
    public void writeEsBulk(List<IndexQuery> indexQueryList) {

        template.bulkIndex(indexQueryList);
    }
}
