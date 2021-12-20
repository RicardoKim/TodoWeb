package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.TodoDTO;
import com.example.demo.model.TodoEntity;
import com.example.demo.service.TodoService;

@RestController
@RequestMapping("todo")
public class TodoController {
	
	@Autowired
	private TodoService service; 
	/*
	 service�� TodoService�� ���� ���ǵ� ��ü�̴�. �̷��� ��ü�� TodoController�� �������� ������ ��ü�ν� 
	 ������ private TodoService service = new TodoService();�� ȣ���ؾ��Ѵ�
	 ������ �ս� TodoService�� Componentȭ �߾��� �̸� Autowire�� �����Ŵ���ν� ������ ������ �Ͼ�� �� ���� �� �� �ִ�. 
	 */
	
	@GetMapping("/test")
	public ResponseEntity<?> testTodo(){
		String str = service.testService();
		//�ս� ������ ������ ��ü�� service�� ����Ͽ� �ش� ���񽺿��� ����ϰ��� �ϴ� ���񽺸� ȣ���ؼ� �����´�.
		List<String> list = new ArrayList<>();
		list.add(str);
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
		return ResponseEntity.ok().body(response);
	}
	@PostMapping
	public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto){
		try {
			String temporaryUserId = "temporary-user";
			
			// (1) ���ڷ� �޾ƿ� DTO�� ��ƼƼ�� ��ȭ�����ش�.
			TodoEntity entity = TodoDTO.toEntity(dto);
			
			// (2) id�� null�� �ʱ�ȭ�Ѵ�. ���� ��ÿ��� id�� ���� �̸� �޾ƿ��� �����̴�.
			entity.setId(null);
			
			// (3) �ӽ� ����� ���̵� �������ش�. ����� ����Ʈ�� ���� ������ �̸� �޾ƿ� ���� ���⿡ �̸� ���������� ��ü�Ѵ�.
			entity.setUserId(temporaryUserId);
			
			// (4) ���񽺸� �̿��� Todo ��ƼƼ�� �����Ѵ�.
			List<TodoEntity> entities = service.create(entity);
			
			// (5) �ڹٽ�Ʈ���� �̿��� ���ϵ� ��ƼƼ ����Ʈ�� TodoDTO ����Ʈ�� ��ȯ�Ѵ�.
			List<TodoDTO> dtoList = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
			
			// (6) ��ȯ�� TodoDTO ����Ʈ(dtoList)�� �̿��� ResponseDTO�� �ʱ�ȭȯ��.
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtoList).build();
			
			// (7) ResponseDTO�� �����Ѵ�.
			return ResponseEntity.ok().body(response);
			
		}catch(Exception e) {
			// (8) ���� ���� �޼����� �ִٸ� �̸� ��� client���� ��ȯ�Ѵ�.
			String error = e.getMessage();
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@PutMapping
	public ResponseEntity<?> updateTodo(@RequestBody TodoDTO dto){
		String temporaryUserId = "temporary-user";
		
		TodoEntity entity = TodoDTO.toEntity(dto);
		
		entity.setUserId(temporaryUserId);
		
		List<TodoEntity> entities = service.create(entity);
		
		// (5) �ڹٽ�Ʈ���� �̿��� ���ϵ� ��ƼƼ ����Ʈ�� TodoDTO ����Ʈ�� ��ȯ�Ѵ�.
		List<TodoDTO> dtoList = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
		
		// (6) ��ȯ�� TodoDTO ����Ʈ(dtoList)�� �̿��� ResponseDTO�� �ʱ�ȭȯ��.
		ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtoList).build();
		
		// (7) ResponseDTO�� �����Ѵ�.
		return ResponseEntity.ok().body(response);
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteTodo(@RequestBody TodoDTO dto){
		try {
			String temporaryUserId = "temporary-user";
			// (1) ���ڷ� �޾ƿ� DTO�� ��ƼƼ�� ��ȭ�����ش�.
			TodoEntity entity = TodoDTO.toEntity(dto);
			entity.setUserId(temporaryUserId);
			
			// (4) ���񽺸� �̿��� Todo ��ƼƼ�� �����Ѵ�.
			List<TodoEntity> entities = service.delete(entity);
			
			// (5) �ڹٽ�Ʈ���� �̿��� ���ϵ� ��ƼƼ ����Ʈ�� TodoDTO ����Ʈ�� ��ȯ�Ѵ�.
			List<TodoDTO> dtoList = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
			
			// (6) ��ȯ�� TodoDTO ����Ʈ(dtoList)�� �̿��� ResponseDTO�� �ʱ�ȭȯ��.
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtoList).build();
			
			// (7) ResponseDTO�� �����Ѵ�.
			return ResponseEntity.ok().body(response);
			
		}catch(Exception e) {
			// (8) ���� ���� �޼����� �ִٸ� �̸� ��� client���� ��ȯ�Ѵ�.
			String error = e.getMessage();
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	
	}
}
