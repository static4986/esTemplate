package com.cn.yongrui.es.repository;

import com.cn.yongrui.es.entity.Student;
import com.cn.yongrui.es.entity.Writeable;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.List;
import java.util.Map;

public interface StudentReadRespository {

    List<Student> repositoryStore(Student student);

    Long queryCount(Writeable writeable);

    Double querySum(Writeable writeable,String field);

    Double queryMax(Writeable writeable,String field);

    Double queryAvg(Writeable writeable,String field);

    Double queryGroup(Writeable writeable,String field);

    Map<String,Object> queryState(Writeable writeable,String field);
}
