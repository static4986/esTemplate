package com.cn.yongrui.es.support;

import com.cn.yongrui.es.service.Impl.forkService.ForkPojoObj;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class ForkListQuery {

    public void execute(){

        isForked=test.apply(MAX);

        if(isForked){
            List<ForkPojoObj> list=forkMethod.apply((ForkPojoObj)originalPojo);
            for (ForkPojo p:list) {
                execute();
            }
        }else {
            //put redis
        }
    }

    public void run(){
        /*while(redis.list>0){
            ForkPojo forkPojo=redis.list.pop;
            dealMethod.apply(forkPojo);
        }*/
    }

    Function<Integer,Boolean> test;

    Function<ForkPojoObj,List<ForkPojoObj>> forkMethod;

    Function<ForkPojo,Void> dealMethod;

    private final Integer MAX;

    private ForkPojo originalPojo;

    boolean isForked=false;

    public ForkListQuery(Integer MAX, ForkPojo pojo) {
        this.MAX = MAX;
        this.originalPojo=pojo;
    }

    public ForkListQuery buildTest(Function<Integer,Boolean> t) {
        test=t;
        return this;
    }

    public ForkListQuery buildFork(Function<ForkPojoObj,List<ForkPojoObj>> t) {
        forkMethod = t;
        return this;
    }

    public ForkListQuery buildDeal(Function<ForkPojo,Void> t) {
        dealMethod = t;
        return this;
    }

    Function<ForkPojoObj,List<ForkPojoObj>> defaultForkMethod= x->{
        final long t1=x.getEnd().toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        final long t2=x.getBegin().toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        final long ternal=(t1-t2)/100;
        List<ForkPojoObj> list=new ArrayList<>(100);
        for(int i=0;i<100;i++){
            ForkPojoObj pojoObj=new ForkPojoObj(t1+i*ternal,t1+(i+1)*ternal);
            if(i==99)pojoObj.setEnd(t2);
            list.add(pojoObj);
        }
        return list;
    };

}
