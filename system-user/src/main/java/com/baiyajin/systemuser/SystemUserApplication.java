package com.baiyajin.systemuser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
	@ComponentScan(basePackages = "com.baiyajin.systemuser")
	@MapperScan(value = "com.baiyajin.systemuser.mapper")
	public class SystemUserApplication {

		public static void main(String[] args) {
			SpringApplication.run(SystemUserApplication.class, args);
		}


}
