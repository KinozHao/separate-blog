package com.kinoz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
/**
 * @Author Hao Kinoz
 * @Description Swagger开头的描述信息
 * @Date 2023/3/31
 **/
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket customDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kinoz.controller"))
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("JSEI-IntelliLab", "https://github.com/KinozHao", "kinozhao0715@gmail.com");
        return new ApiInfoBuilder()
                .title("个人博客系统")
                .description("基于Springboot2")
                .contact(contact)   // 联系方式
                .version("1.1.0")  // 版本
                .build();
    }
}