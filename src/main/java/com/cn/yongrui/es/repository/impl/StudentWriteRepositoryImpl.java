package com.cn.yongrui.es.repository.impl;

import com.cn.yongrui.es.entity.Student;
import com.cn.yongrui.es.entity.Writeable;
import com.cn.yongrui.es.repository.StudentWriteRepository;
import com.cn.yongrui.es.service.WriteStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class StudentWriteRepositoryImpl implements StudentWriteRepository {

    @Autowired
    private WriteStore writeStore;

    @Override
    public void repositoryStore(Student student) {

        IndexQuery indexQuery=new IndexQuery();
        indexQuery.setIndexName(Student.indexName);
        indexQuery.setType(Student.indexType);
        indexQuery.setId(student.getSid());
        indexQuery.setObject(student);
        writeStore.writeEs(indexQuery);

    }

    @Override
    public void repositoryBulkInsert(List<Student> studentList) {

        List<IndexQuery> list=new ArrayList<>();
        for (Student s:studentList){
            IndexQuery indexQuery=new IndexQuery();
            indexQuery.setIndexName(Student.indexName);
            indexQuery.setType(Student.indexType);
            indexQuery.setId(s.getSid());
            indexQuery.setObject(s);

            list.add(indexQuery);
        }
        writeStore.writeEsBulk(list);
    }

}
