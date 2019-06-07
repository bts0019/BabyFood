package com.qfedu.babyfood.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    public ApiInfo createA(){
        ApiInfo info = new ApiInfoBuilder().title("优之优数据接口").contact(
                new Contact("babyfood","http://1000phone.com",
                        "15837005426@163.com")).description("实现优之优用户项目的数据接口信息").build();
        return info;
    }

    @Bean
    public Docket createDoucket(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2).apiInfo(
                createA()).select().apis(RequestHandlerSelectors.basePackage("com.qfedu.babyfood.controller")).build();
        return docket;
    }
}
