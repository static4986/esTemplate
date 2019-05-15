package com.cn.yongrui.es.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {

    @Bean
    public GlobalHeadersFilter globalHeadersFilter(){
        return new GlobalHeadersFilter();
    }

    @Bean
    public FilterRegistrationBean globalRegisterFilter(){
        FilterRegistrationBean registrationBean=new FilterRegistrationBean();
        registrationBean.setFilter(globalHeadersFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("globalRegisterFilter");
        return registrationBean;
    }
}
