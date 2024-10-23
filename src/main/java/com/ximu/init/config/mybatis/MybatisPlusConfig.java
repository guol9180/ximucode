package com.ximu.init.config.mybatis;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: 析木
 * @description: mybatis plus 配置：
 *      注解@MapperScan，扫描指定包中的mapper接口，并将其注册为mybatis的mapper；
 *          MyBatis 使用 Java 的动态代理（基于接口的代理）（或 CGLIB 基于类的代理）来实现 Mapper 接口的代理。
 *          每个 Mapper 接口都有一个代理对象，代理对象会拦截方法调用并执行相应的 SQL 语句。
 */
@Configuration
@MapperScan("com.ximu.init.mapper")
public class MybatisPlusConfig {

    /**
     * 拦截器配置：
     *  创建拦截器，启动分页插件，自动识别数据库
     * @return MybatisPlusInterceptor
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
