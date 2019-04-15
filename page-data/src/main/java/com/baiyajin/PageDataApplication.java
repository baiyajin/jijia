package com.baiyajin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.baiyajin.mapper.*")
public class PageDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(PageDataApplication.class, args);
	}

}
