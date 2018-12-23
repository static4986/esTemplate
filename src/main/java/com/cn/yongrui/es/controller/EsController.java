package com.cn.yongrui.es.controller;


import com.cn.yongrui.es.entity.Student;
import com.cn.yongrui.es.esControl.EsControl;
import com.cn.yongrui.es.mock.MockStudentList;
import com.cn.yongrui.es.repository.StudentReadRespository;
import com.cn.yongrui.es.repository.StudentWriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/es")
public class EsController {


    @Autowired
    private StudentWriteRepository studentWriteRepository;

    @Autowired
    private StudentReadRespository readRespository;

    /*
    * 查询数据
    * */
    @RequestMapping(value = "/queryList",method = RequestMethod.POST)
    @ResponseBody
    public List<Student> queryEs(Student student){
        List<Student> students = readRespository.repositoryStore(student);
        return students;
    }
    /*
    * 插入数据
    * */
    @RequestMapping(value = "/insert/{turn}",method = RequestMethod.GET)
    public String insertEs(@PathVariable Integer turn){
        Long firstDate=System.currentTimeMillis();
        List<Student> students = MockStudentList.mockStudent(turn);
        Long mockDate=System.currentTimeMillis();
        for (Student s:students) {
            studentWriteRepository.repositoryStore(s);
        }
        Long insertDate=System.currentTimeMillis();
        Long inset=mockDate-firstDate;
        Long mock=insertDate-mockDate;
        System.out.println("mock "+turn+"条数据，用时"+inset);
        System.out.println("insert "+turn+"条数据，用时"+mock);

        return "success";
    }

    /*
     * 批量插入数据
     * */
    @RequestMapping(value = "/insertBulk/{turn}",method = RequestMethod.GET)
    public String insertEsBulk(@PathVariable Integer turn){
        Long firstDate=System.currentTimeMillis();
        List<Student> students = MockStudentList.mockStudent(turn);
        Long mockDate=System.currentTimeMillis();

            studentWriteRepository.repositoryBulkInsert(students);

        Long insertDate=System.currentTimeMillis();
        Long inset=mockDate-firstDate;
        Long mock=insertDate-mockDate;
        System.out.println("mock "+turn+"条数据，用时"+inset);
        System.out.println("bulkInsert "+turn+"条数据，用时"+mock);

        return "success";
    }

    /*
     * 查询数量
     * */
    @RequestMapping(value = "/queryCount",method = RequestMethod.POST)
    public Long queryCount(Student student){

        Long count = readRespository.queryCount(student);
        return count;
    }

    /*
     * 查询总和
     * */
    @RequestMapping(value = "/querySum/{field}",method = RequestMethod.POST)
    public Double querySum(Student student,@PathVariable String field){
        Double sum = readRespository.querySum(student,field);
        return sum;
    }

    /*
     * 查询最大值
     * */
    @RequestMapping(value = "/queryMax/{field}",method = RequestMethod.POST)
    public Double queryMax(Student student,@PathVariable String field){
        Double max = readRespository.queryMax(student,field);
        return max;
    }


    /*
     * 查询平均值
     * */
    @RequestMapping(value = "/queryAvg/{field}",method = RequestMethod.POST)
    public Double queryAvg(Student student,@PathVariable String field){
        Double avg = readRespository.queryAvg(student,field);
        return avg;
    }

    /*
     * 查询分组
     * */
    @RequestMapping(value = "/queryGroup/{field}",method = RequestMethod.POST)
    public Double queryGroup(Student student,@PathVariable String field){
        Double group = readRespository.queryGroup(student,field);
        return group;
    }

    /*
     * 查询五项基本信息
     * */
    @RequestMapping(value = "/queryState/{field}",method = RequestMethod.POST)
    public Map<String, Object> queryState(Student student,@PathVariable String field){
        Map<String, Object> stateMap = readRespository.queryState(student, field);
        return stateMap;
    }


    /*
    * 删除index
    * */
    @RequestMapping(value = "/delete/{indexName}",method = RequestMethod.DELETE)
    public Boolean deleteIndex(@PathVariable String indexName){
        Boolean result = esControl.indexDelete(indexName);
        return result;
    }


    /*
    * 实例化index
    * */
    @RequestMapping(value = "/init",method = RequestMethod.GET)
    public void esInit(){
        esControl.indexInit();
    }

    @Autowired
    private EsControl esControl;
}
