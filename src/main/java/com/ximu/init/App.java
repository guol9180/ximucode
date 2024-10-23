package com.ximu.init;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author: 析木
 * @description: 主启动类（项目入口）
 *          1.注解@EnableScheduling，开启spring定时任务调度功能；
 *          2.注解@SpringBootApplication，核心注解，实际上是以下三个注解的组合：
 *              注解@Configuration：表示该类是一个 Spring 配置类，能够定义 Spring 的 bean。
 *              注解@EnableAutoConfiguration：启用 Spring Boot 的自动配置功能，根据项目的依赖自动配置 Spring 应用所需的组件，简化了配置过程。
 *              注解@ComponentScan：启用组件扫描，自动扫描当前包及其子包中的 Spring 组件（如 @Component、@Service、@Repository、@Controller 等），
 *              并将其注册为 Spring 的 bean。
 *          3.注解@EnableAspectJAutoProxy，开启对AspectJ风格的AOP支持。
 *              proxyTargetClass：代理类型，默认false即jdk动态代理，反之为cglib动态代理。
 *              exposeProxy：暴露代理，默认false即不暴露代理对象给目标对象，反之目标可以通过
 *              AopProxyUtils.ultimateTargetClass() 方法获取到代理对象，适用于需要在切面中访问代理对象的场景。
 */
@EnableScheduling
// TODO 如需开启 Redis，须移除 exclude 中的内容
@SpringBootApplication(exclude = {RedisAutoConfiguration.class})
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class App 
{
    public static void main( String[] args ) {
        SpringApplication.run(App.class, args);
    }
}
