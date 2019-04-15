package com.baiyajin.pagedata;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(value = "com.baiyajin.pagedata.mapper")
public class PageDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(PageDataApplication.class, args);
	}

}
