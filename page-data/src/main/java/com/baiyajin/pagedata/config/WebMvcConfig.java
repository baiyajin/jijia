package com.baiyajin.pagedata.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.charset.Charset;
import java.util.List;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    /*
    * 解决ajax get请求乱码
    * */

    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return converter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(responseBodyConverter());
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        把classpath:/static/下面的所有资源显露对外可以访问,以这种方式就可以访问http://localhost:8888/static/image/CODE.jpg
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");


        //将linux系统下的目录映射到spring boot 项目中
        registry.addResourceHandler("/file/**").addResourceLocations("file:/home/");
        //将本地目录映射到spring boot 项目中
//        registry.addResourceHandler("/file/**").addResourceLocations("classpath:/static/business/");

    }


   /* *//*提高用户体验之自定义错误界面*//*
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
                ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/baiyajin/static/error/401.html");
                ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/baiyajin/static/error/404.html");
                ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/baiyajin/static/error/500.html");
                configurableEmbeddedServletContainer.addErrorPages(error401Page, error404Page, error500Page);
            }
        };
    }*/


}
