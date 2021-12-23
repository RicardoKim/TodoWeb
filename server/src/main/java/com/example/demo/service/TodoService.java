package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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
	
	public List<TodoEntity> update(final TodoEntity entity){
		validate(entity);
		//존재하지 않는 엔티티는 업데이트할 수 없기 때문에 validate처리를 해준다.
		final Optional<TodoEntity> original = repository.findById(entity.getId());
		// 반환된 TodoEntity가 존재하면 값을 새 entity 값으로 덮어 씌운다.
		original.ifPresent(todo ->{
			todo.setTitle(entity.getTitle());
			todo.setDone(entity.isDone());
			// 데이터 베이스에 새 값을 저장한다.
			repository.save(todo);
		});
		// retrieve todo에서 만든 메서드를 이용해 사용자의 모든 Todo 리스트를 반환한다.
		return retrieve(entity.getUserId());
	}
	
	public List<TodoEntity> delete(final TodoEntity entity){
		validate(entity);
		
		try {
			repository.delete(entity);
			
		}catch(Exception e) {
			log.error("error deleting entity ", entity.getId(), e);
			throw new RuntimeException("error deleting entity " + entity.getId());
		}
		
		return retrieve(entity.getUserId());
	}
	
	public List<TodoEntity> retrieve(final String userId){
		validate(userId);
		return repository.findByUserId(userId);
	}
	
	
	// 검증 관련 함수는 다른 부분에서도 자신들 나름대로 검증을 진행할 것이기 때문에 이를 private 메소드로 만들어
	// 다른 validate 함수와 겹치지 않게 한다.
	private void validate(final TodoEntity entity) {
		if(entity == null) {
			log.warn("Entity cannot be null");
			throw new RuntimeException("Entity cannot be null");
			
		}
		
		if(entity.getUserId() == null) {
			log.warn("Unknown user.");
			throw new RuntimeException("Unknown user.");
		}
	}
	
	//오버로딩을 사용해서 retrieve에서 userId가 null이면 로그를 남기는 메소드를 만들었다.
	// 아마도 나중에 토큰 만료나 이런데서 쓸 수 있을거 같아 추가해서 만들었다.
	private void validate(final String userId) {
		if(userId == null) {
			log.warn("Unknown user.");
			throw new RuntimeException("Unknown user.");
			
		}
	}
	
	// userId를 받아서 userId에 해당하는 Todo를 모두 삭제하는 함수
	// @Transactional 필요한지?
	public void deleteAll(String userId) {
		validate(userId);
		repository.deleteAllByUserId(userId);
	}
	
}
