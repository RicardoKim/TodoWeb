package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication 
/*
 * 여러 가지의 역할이 있다.
 * 1. 애플리케이션을 H2 데이터베이스에 연결해준다.W
 */
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
