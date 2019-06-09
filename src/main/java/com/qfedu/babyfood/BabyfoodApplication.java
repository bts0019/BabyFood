package com.qfedu.babyfood;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@MapperScan("com.qfedu.babyfood.dao") //扫描Mybatis修饰的接口所在的包
@ImportResource(locations={"classpath:mykaptcha.xml"})
@SpringBootApplication
@EnableSwagger2 //启用Swagger
public class BabyfoodApplication {

    public static void main(String[] args) {
        SpringApplication.run(BabyfoodApplication.class, args);
    }

}
