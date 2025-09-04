package com.example.classicimmortalserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.classicimmortalserver.mapper")
public class ClassicImmortalServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClassicImmortalServerApplication.class, args);
	}

}
