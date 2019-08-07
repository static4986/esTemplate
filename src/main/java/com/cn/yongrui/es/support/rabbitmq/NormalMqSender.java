package com.cn.yongrui.es.support.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class NormalMqSender {

    Logger log=LoggerFactory.getLogger(NormalMqSender.class);

    @Autowired
    private RabbitTemplate template;

    public void send(String contex){
        String date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        contex="hell my friend!"+date;
        log.info(String.format("mq contex is : %s",contex));
        this.template.convertAndSend(RabbitConfig.QUEUE_NAME,contex);
    }
}
