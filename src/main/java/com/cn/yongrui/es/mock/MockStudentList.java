package com.cn.yongrui.es.mock;


import com.cn.yongrui.es.entity.Student;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class MockStudentList {

    public static List<Student> mockStudent(Integer turn) {

        List<Student> stuList=new ArrayList<>();
        for (int i = 0; i < turn; i++) {
            Student student = new Student();
            student.setSid(UUID.randomUUID().toString());
            student.setAge(i%3+12);
            student.setBirthday(new Date());
            student.setName("Amy"+i%10);
            student.setGrade(String.valueOf(i%3+7));
            stuList.add(student);
        }
        return stuList;
    }
}
