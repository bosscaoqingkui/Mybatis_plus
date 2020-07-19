package com.huawei.it.config;

import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.springframework.aop.interceptor.PerformanceMonitorInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import sun.misc.PerformanceLogger;

/**
 * @ClassName MyBatisPlusConfig
 * Description TODO
 * @Author 曹先生
 * @Date 2020/7/19 11:00
 * @Version 1.0
 **/
@EnableTransactionManagement  //自动事务配置
@Configuration
public class MyBatisPlusConfig {
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    //分页组件
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    //逻辑删除
    @Bean
    public ISqlInjector sqlInjector() {
        return new DefaultSqlInjector();
    }

    //sql执行效率插件
    @Bean
    @Profile({"dev","test"}) //设置dev test 环境开启，保证我们的效率
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor interceptor = new PerformanceInterceptor();
        //在工作中，不允许用户等待超过多长时间，这样我们根据这个进行sql优化
        interceptor.setMaxTime(1000);//（1表示为1毫秒）设置sql的执行的最大时间，如果超过了，则不执行
        interceptor.setFormat(true);//SQL格式化
        return interceptor;
    }

}
