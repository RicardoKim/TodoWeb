package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service //스프링 컴포넌트라는 것과 기능적으로는 비지니스 로직을 수행하는 서비스레이어임을 알려주는 어노테이션이다.
public class TodoService {
	public String testService() {
		return "Test Service";
	}
}
