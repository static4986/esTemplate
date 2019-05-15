package com.cn.yongrui.es.service.Impl;

import com.cn.yongrui.es.entity.Writeable;
import com.cn.yongrui.es.enums.AggEnum;
import com.cn.yongrui.es.service.ReadStore;
import org.elasticsearch.search.aggregations.metrics.avg.InternalAvg;
import org.elasticsearch.search.aggregations.metrics.max.InternalMax;
import org.elasticsearch.search.aggregations.metrics.stats.InternalStats;
import org.elasticsearch.search.aggregations.metrics.sum.InternalSum;
import org.elasticsearch.search.aggregations.metrics.valuecount.InternalValueCount;
import org.elasticsearch.search.aggregations.metrics.valuecount.ValueCount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ReadStoreImpl implements ReadStore {

    Logger logger= LoggerFactory.getLogger(ReadStoreImpl.class);

    @Autowired(required = false)
    private ElasticsearchTemplate template;

    @Override
    public List<? extends Writeable> readEs(SearchQuery searchQuery, Writeable writeable) {
        logger.info(String.format("query for es %s",searchQuery.toString()));

        List<? extends Writeable> writeables = template.queryForList(searchQuery, writeable.getClass());

        return writeables;
    }

    @Override
    public Long queryCount(SearchQuery searchQuery, Writeable writeable) {
        Long count = template.query(searchQuery,
                response -> {
                    InternalValueCount valueCount = (InternalValueCount) response.getAggregations()
                            .asList()
                            .get(0);
                    return valueCount.getValue();
                });
        return count;
    }

    @Override
    public Double querySum(SearchQuery searchQuery) {
        Double sumAmount = template.query(searchQuery,
                                           response -> {
                                                        InternalSum sum = (InternalSum)response.getAggregations()
                                                                                               .asList()
                                                                                               .get(0);
                                                        return sum.getValue();
                                                       });
        return sumAmount;
    }

    @Override
    public Double queryMax(SearchQuery searchQuery) {
        Double max = template.query(searchQuery,
                response -> {
                    InternalMax value = (InternalMax) response.getAggregations()
                            .asList()
                            .get(0);
                    return value.getValue();
                });
        return max;
    }

    @Override
    public Double queryAvg(SearchQuery searchQuery) {
        Double avg = template.query(searchQuery,
                response -> {
                    InternalAvg value = (InternalAvg) response.getAggregations()
                            .asList()
                            .get(0);
                    return value.getValue();
                });
        return avg;
    }


    @Override
    public Double queryGroup(SearchQuery searchQuery) {
        Double avg = template.query(searchQuery,
                response -> {
                    InternalAvg value = (InternalAvg) response.getAggregations()
                            .asList()
                            .get(0);
                    return value.getValue();
                });
        return avg;
    }

    @Override
    public Map<String,Object> queryState(SearchQuery searchQuery) {
        InternalStats states = template.query(searchQuery,
                response -> {
                    InternalStats value = (InternalStats) response.getAggregations()
                            .asList()
                            .get(0);
                    return value;
                });
        long count = states.getCount();
        double avg = states.getAvg();
        double max = states.getMax();
        double min = states.getMin();
        double sum = states.getSum();
        Map<String,Object> stateMap=new HashMap<>();
        stateMap.put(AggEnum.AGG_COUNT.getCode(),count);
        stateMap.put(AggEnum.AGG_MAX.getCode(),max);
        stateMap.put(AggEnum.AGG_MIN.getCode(),min);
        stateMap.put(AggEnum.AGG_SUM.getCode(),sum);
        stateMap.put(AggEnum.AGG_AVG.getCode(),avg);
        return stateMap;
    }

}
