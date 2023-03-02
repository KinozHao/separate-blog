package com.kinoz.config;


import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author kinoz
 * @Date 2023/3/2 19:54
 * @apiNote fastjson消息转换器
 * 解决前端页面时间显示格式的问题
 */
@Configuration
public class FastJsonConfig {

    @Bean
    /**
     * 此方法已被@JsonFormat取代
     * 但是JsonFormat返给前端的数据存在问题
     * @see com.kinoz.domain.pojo.Article
     * @return
     */
    public HttpMessageConverter fastJsonHttpMessageConverters() {
        //1.需要定义一个Convert转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        com.alibaba.fastjson.support.config.FastJsonConfig fastJsonConfig = new com.alibaba.fastjson.support.config.FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");

        SerializeConfig.globalInstance.put(Long.class, ToStringSerializer.instance);

        fastJsonConfig.setSerializeConfig(SerializeConfig.globalInstance);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        HttpMessageConverter<?> converter = fastConverter;
        return converter;
    }
}
