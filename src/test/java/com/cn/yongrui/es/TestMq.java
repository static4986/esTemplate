package com.cn.yongrui.es;

import com.cn.yongrui.es.support.rabbitmq.NormalMqSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMq {

    @Autowired
    private NormalMqSender sender;

    @Test
    public void testNormalMqSender(){
        sender.send("hello my friend,this is my first message!");
    }
}
