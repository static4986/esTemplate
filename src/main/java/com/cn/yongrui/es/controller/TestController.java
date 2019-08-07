package com.cn.yongrui.es.controller;


import com.cn.yongrui.es.entity.TestDateLong;
import com.cn.yongrui.es.support.rabbitmq.NormalMqSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping(value = "/testController")
public class TestController {


    @Autowired
    private ElasticsearchTemplate template;

    @RequestMapping(value = "/insert",method = RequestMethod.GET)
    public String insertBook(String name,Long price,String time){

        Date dateTime=null;
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
             dateTime = sf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        TestDateLong dateLong = new TestDateLong(name,dateTime,dateTime.getTime(),price);
        IndexQuery indexQuery = new IndexQuery();
        indexQuery.setIndexName("testdatelong");
        indexQuery.setType("book");
        indexQuery.setObject(dateLong);
        String index = template.index(indexQuery);
        return index;

    }

    @RequestMapping(value = "/testCaculate",method = RequestMethod.GET)
    public Date caculate(String date,long offset,long interval){
        Date dateTime=null;
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            dateTime = sf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        double resule=Math.floor((dateTime.getTime() - offset) / interval) * interval + offset;

        return DoubleToDate(resule);
    }


    @Autowired
    private NormalMqSender sender;

    @RequestMapping(value = "/testMq",method = RequestMethod.GET)
    public void testMq(){
            sender.send("hello my friend,this is my first message!");

    }

    public static Date DoubleToDate(Double dVal){
        Date oDate = new Date();
        @SuppressWarnings("deprecation")
        long localOffset = oDate.getTimezoneOffset() * 60000; //系统时区偏移 1900/1/1 到 1970/1/1 的 25569 天
        oDate.setTime((long) ((dVal - 25569) * 24 * 3600 * 1000 + localOffset));

        return oDate;
    }


}
