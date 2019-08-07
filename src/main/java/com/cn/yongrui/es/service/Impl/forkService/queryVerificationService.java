package com.cn.yongrui.es.service.Impl.forkService;

import com.cn.yongrui.es.support.ForkListQuery;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class queryVerificationService {

    private Integer MAX=10000;

    ForkPojoObj forkPojoObj=new ForkPojoObj(LocalDateTime.now(),LocalDateTime.now());

    public void query() {
        ForkListQuery forkListQuery = new ForkListQuery(MAX, forkPojoObj);

        Integer count=10;//query count from ***;
        forkListQuery.buildTest(x->x>count)
                     .buildFork(x->{
                         final long t1=x.getEnd().toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
                         final long t2=x.getBegin().toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
                         final int size=count/MAX;
                         final long ternal=(t1-t2)/size;
                         List<ForkPojoObj> list=new ArrayList<>(size);
                         for(int i=0;i<size;i++){
                             ForkPojoObj pojoObj=new ForkPojoObj(t1+i*ternal,t1+(i+1)*ternal);
                             if(i==size-1)pojoObj.setEnd(t2);
                             list.add(pojoObj);
                         }
                         return list;
                     });
                     //.buildDeal(x->dealwith(x));


        //if(redis.list>0){
          if(true){
            forkListQuery.run();
        }else {
            forkListQuery.execute();
            forkListQuery.run();
        }
    }



}
