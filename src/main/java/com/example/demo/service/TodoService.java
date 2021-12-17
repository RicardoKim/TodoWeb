package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.TodoEntity;
import com.example.demo.persistence.TodoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service //스프링 컴포넌트라는 것과 기능적으로는 비지니스 로직을 수행하는 서비스레이어임을 알려주는 어노테이션이다.
public class TodoService {
	@Autowired
	private TodoRepository repository;

	public String testService() {
		//TodoEntity 생성
		TodoEntity entity = TodoEntity.builder().title("My first todo item").build();
		//TodoEntity 저장
		repository.save(entity);
		//TodoEntity 검색
		TodoEntity savedEntity = repository.findById(entity.getId()).get();
		return savedEntity.getTitle();
	}
	public List<TodoEntity> create(final TodoEntity entity){
		//validation
		validate(entity);
		
		repository.save(entity);
		
		log.info("Entity Id : {} is saved.", entity.getId());
		
		return repository.findByUserId(entity.getUserId());
	}
	
	// 검증 관련 함수는 다른 부분에서도 자신들 나름대로 검증을 진행할 것이기 때문에 이를 private 메소드로 만들어
	// 다른 validate 함수와 겹치지 않게 한다.
	private void validate(final TodoEntity entity) {
		if(entity == null) {
			log.warn("Entity cannot be full");
			throw new RuntimeException("Entity cannot be null");
			
		}
		
		if(entity.getUserId() == null) {
			log.warn("Unknown user.");
			throw new RuntimeException("Unknown user.");
		}
	}
}
