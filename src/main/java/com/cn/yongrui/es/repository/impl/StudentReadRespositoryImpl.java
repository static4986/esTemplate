package com.cn.yongrui.es.repository.impl;

import com.cn.yongrui.es.builder.StudentQueryBuilder;
import com.cn.yongrui.es.entity.Student;
import com.cn.yongrui.es.entity.Writeable;
import com.cn.yongrui.es.repository.StudentReadRespository;
import com.cn.yongrui.es.service.ReadStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class StudentReadRespositoryImpl implements StudentReadRespository {

    @Autowired
    private ReadStore readStore;

    @Override
    public List<Student> repositoryStore(Student student) {
        StudentQueryBuilder studentQueryBuilder=new StudentQueryBuilder(student);
        NativeSearchQuery nativeSearchQuery = studentQueryBuilder.nativeSearchQuery;
        List<Student> writeables = (List<Student>)readStore.readEs(nativeSearchQuery, student);
        return writeables;
    }

    @Override
    public Long queryCount(Writeable writeable) {
        StudentQueryBuilder studentQueryBuilder=new StudentQueryBuilder((Student)writeable);
        NativeSearchQuery nativeSearchQuery = studentQueryBuilder.nativeSearchQuery;
        NativeSearchQuery nativeSearchQuery1 = studentQueryBuilder.countAggBuilder(nativeSearchQuery);
        Long count = readStore.queryCount(nativeSearchQuery1, writeable);
        return count;
    }

    @Override
    public Double querySum(Writeable writeable,String field) {
        StudentQueryBuilder studentQueryBuilder=new StudentQueryBuilder((Student)writeable);
        NativeSearchQuery nativeSearchQuery = studentQueryBuilder.nativeSearchQuery;
        NativeSearchQuery nativeSearchQuery1 = studentQueryBuilder.sumAggBuilder(nativeSearchQuery,field);
        Double count = readStore.querySum(nativeSearchQuery1);
        return count;
    }

    @Override
    public Double queryMax(Writeable writeable, String field) {
        StudentQueryBuilder studentQueryBuilder=new StudentQueryBuilder((Student)writeable);
        NativeSearchQuery nativeSearchQuery = studentQueryBuilder.nativeSearchQuery;
        NativeSearchQuery nativeSearchQuery1 = studentQueryBuilder.maxAgg(nativeSearchQuery,field);
        Double max = readStore.queryMax(nativeSearchQuery1);
        return max;
    }

    @Override
    public Double queryAvg(Writeable writeable, String field) {
        StudentQueryBuilder studentQueryBuilder=new StudentQueryBuilder((Student)writeable);
        NativeSearchQuery nativeSearchQuery = studentQueryBuilder.nativeSearchQuery;
        NativeSearchQuery nativeSearchQuery1 = studentQueryBuilder.avgAgg(nativeSearchQuery,field);
        Double avg = readStore.queryAvg(nativeSearchQuery1);
        return avg;
    }

    @Override
    public Double queryGroup(Writeable writeable, String field) {
        // FIXME: 2018/12/24
        StudentQueryBuilder studentQueryBuilder=new StudentQueryBuilder((Student)writeable);
        NativeSearchQuery nativeSearchQuery = studentQueryBuilder.nativeSearchQuery;
        NativeSearchQuery nativeSearchQuery1 = studentQueryBuilder.filterAgg(nativeSearchQuery,field);
        Double group = readStore.queryGroup(nativeSearchQuery1);
        return group;
    }

    @Override
    public Map<String, Object> queryState(Writeable writeable, String field) {
        StudentQueryBuilder studentQueryBuilder=new StudentQueryBuilder((Student)writeable);
        NativeSearchQuery nativeSearchQuery = studentQueryBuilder.nativeSearchQuery;
        NativeSearchQuery nativeSearchQuery1 = studentQueryBuilder.stateAgg(nativeSearchQuery,field);
        Map<String, Object> stateMap = readStore.queryState(nativeSearchQuery1);
        return stateMap;
    }
}
