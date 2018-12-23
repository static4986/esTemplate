package com.cn.yongrui.es.esControl;


import com.cn.yongrui.es.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EsControl {

    @Autowired(required = false)
    private ElasticsearchTemplate template;

    /*
    * 实例化index
    * */
    public void indexInit(){
        List<String> list=new ArrayList();
        list.add(Student.indexName);
        for (String indexName:list) {
            boolean exit = template.indexExists(indexName);
            if(!exit)template.createIndex(indexName);
        }
    }



    /*
    * 删除index
    * */
    public Boolean indexDelete(String indexName){
        Boolean result=template.deleteIndex(indexName);
        return result;
    }
}
