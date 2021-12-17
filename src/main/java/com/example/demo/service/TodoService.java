package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.TodoEntity;
import com.example.demo.persistence.TodoRepository;

@Service //스프링 컴포넌트라는 것과 기능적으로는 비지니스 로직을 수행하는 서비스레이어임을 알려주는 어노테이션이다.
public class TodoService {
	@Autowired
	private TodoRepository repository;

	public String testService() {
		TodoEntity entity = TodoEntity.builder().title("My first todo item").build();
		repository.save(entity);
		TodoEntity savedEntity = repository.findById(entity.getId()).get();
		return savedEntity.getTitle();
	}
}
