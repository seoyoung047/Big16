package com.google.phonebook.controller;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean<Filter> logFilter() {
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();

        //필터 등록
        filterFilterRegistrationBean.setFilter(new LogFilter());

        // 필터 우선순위 설정
        filterFilterRegistrationBean.setOrder(1);

        // 필터를 적용할 요청 URL
        filterFilterRegistrationBean.addUrlPatterns("/phonebook/*");

        filterFilterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ERROR);

        return filterFilterRegistrationBean;
    }
    
    @Bean
    public FilterRegistrationBean<Filter> loginCheckFilter() {
      FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
      filterFilterRegistrationBean.setFilter(new LoginCheckFilter());
      filterFilterRegistrationBean.setOrder(2);
      filterFilterRegistrationBean.addUrlPatterns("/phonebook/login/*");
      return filterFilterRegistrationBean;
    }

}