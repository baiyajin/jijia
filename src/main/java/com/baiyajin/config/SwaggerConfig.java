package com.baiyajin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author xiaqing
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig{

    /*这个要用pom 2.7.0*/
    /*<dependency> &lt;!&ndash; API &ndash;&gt;
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger2</artifactId>
			<version>2.7.0</version>
		</dependency>*/
    /*@Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("标题：云南省建设工程材料设备价格监测系统_接口文档")
                        .description("描述：用于运维前端人员查看接口地址")
                        .contact(new Contact("baiyajin", "http://localhost:8888/baiyajin/start/?sweep_code_draw=0", "baiyajin110@qq.com"))
                        .version("版本号:1.0")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.baiyajin.controller"))
                .paths(PathSelectors.any())
                .build();
    }
*/








}
