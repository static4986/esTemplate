package com.cn.yongrui.es.repository;

import com.cn.yongrui.es.entity.Student;

import java.util.List;

public interface StudentWriteRepository {

    void repositoryStore(Student student);

    void repositoryBulkInsert(List<Student> studentList);

}
