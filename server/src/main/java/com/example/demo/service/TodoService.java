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
@Service //������ ������Ʈ��� �Ͱ� ��������δ� �����Ͻ� ������ �����ϴ� ���񽺷��̾����� �˷��ִ� ������̼��̴�.
public class TodoService {
	@Autowired
	private TodoRepository repository;

	public String testService() {
		//TodoEntity ����
		TodoEntity entity = TodoEntity.builder().title("My first todo item").build();
		//TodoEntity ����
		repository.save(entity);
		//TodoEntity �˻�
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
		//�������� �ʴ� ��ƼƼ�� ������Ʈ�� �� ���� ������ validateó���� ���ش�.
		final Optional<TodoEntity> original = repository.findById(entity.getId());
		// ��ȯ�� TodoEntity�� �����ϸ� ���� �� entity ������ ���� �����.
		original.ifPresent(todo ->{
			todo.setTitle(entity.getTitle());
			todo.setDone(entity.isDone());
			// ������ ���̽��� �� ���� �����Ѵ�.
			repository.save(todo);
		});
		// retrieve todo���� ���� �޼��带 �̿��� ������� ��� Todo ����Ʈ�� ��ȯ�Ѵ�.
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
	
	
	// ���� ���� �Լ��� �ٸ� �κп����� �ڽŵ� ������� ������ ������ ���̱� ������ �̸� private �޼ҵ�� �����
	// �ٸ� validate �Լ��� ��ġ�� �ʰ� �Ѵ�.
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
	
	//�����ε��� ����ؼ� retrieve���� userId�� null�̸� �α׸� ����� �޼ҵ带 �������.
	// �Ƹ��� ���߿� ��ū ���ᳪ �̷����� �� �� ������ ���� �߰��ؼ� �������.
	private void validate(final String userId) {
		if(userId == null) {
			log.warn("Unknown user.");
			throw new RuntimeException("Unknown user.");
			
		}
	}
	
	// userId�� �޾Ƽ� userId�� �ش��ϴ� Todo�� ��� �����ϴ� �Լ�
	// @Transactional �ʿ�����?
	public void deleteAll(String userId) {
		validate(userId);
		repository.deleteAllByUserId(userId);
	}
	
}
