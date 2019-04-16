package com.baiyajin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

	/*打war包*/
@SpringBootApplication
@MapperScan(value = "com.baiyajin.mapper.*")
public class PageDataApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(PageDataApplication.class, args);
	}
		@Override
		protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
		{
			return application.sources(PageDataApplication.class);
		}
}




/*@SpringBootApplication
@MapperScan(value = "com.baiyajin.mapper.*")
public class PageDataApplication{

	public static void main(String[] args) {
		SpringApplication.run(PageDataApplication.class, args);
	}
}*/
