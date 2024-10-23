package com.ximu.init.config.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * @author: 析木
 * @description: Spring MVC Json 配置: 防止 Long 转 json 精度丢失问题。
 *  Jackson2ObjectMapperBuilder: 这是一个构建器，用于创建 ObjectMapper 实例。它提供了一些便捷的方法来配置 Jackson。
 *  createXmlMapper(false).build(): 这行代码创建一个新的 ObjectMapper 实例，createXmlMapper(false) 表示不创建 XML Mapper，build() 方法用于构建最终的 ObjectMapper 实例。
 *  SimpleModule: 这是 Jackson 提供的一个模块，用于注册自定义的序列化器和反序列化器。
 *  addSerializer: 这两行代码将 Long 类型和基本类型 long 的序列化器设置为 ToStringSerializer.instance。这意味着在序列化 Long 类型时，Jackson 将使用 ToStringSerializer，将 Long 转换为字符串，从而避免精度丢失。
 *  registerModule(module): 将自定义的模块注册到 ObjectMapper 中，使得在序列化时能够使用自定义的序列化器。
 */
@JsonComponent // 标记该类为 JSON 组件，允许你自定义 Jackson 的配置。
public class JsonConfig {

    /**
     * 添加 Long 转 json 精度丢失的配置
     */
    @Bean
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        // 创建 ObjectMapper 实例
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();

        // 创建一个 SimpleModule，用于注册自定义序列化器
        SimpleModule module = new SimpleModule();

        // 注册 Long 类型的序列化器，使用 ToStringSerializer
        module.addSerializer(Long.class, ToStringSerializer.instance);
        module.addSerializer(Long.TYPE, ToStringSerializer.instance);

        // 将自定义模块注册到 ObjectMapper
        objectMapper.registerModule(module);

        // 返回配置好的 ObjectMapper
        return objectMapper;
    }
}
