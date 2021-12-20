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
	 service는 TodoService에 의해 정의된 객체이다. 이러한 객체는 TodoController가 의존성을 가지는 객체로써 
	 원래는 private TodoService service = new TodoService();로 호출해야한다
	 하지만 앞써 TodoService를 Component화 했었고 이를 Autowire로 연결시킴으로써 의존성 주입을 일어나게 한 것을 알 수 있다. 
	 */
	
	@GetMapping("/test")
	public ResponseEntity<?> testTodo(){
		String str = service.testService();
		//앞써 정의한 의존성 객체인 service를 사용하여 해당 서비스에서 출력하고자 하는 서비스를 호출해서 가져온다.
		List<String> list = new ArrayList<>();
		list.add(str);
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
		return ResponseEntity.ok().body(response);
	}
	@PostMapping
	public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto){
		try {
			String temporaryUserId = "temporary-user";
			
			// (1) 인자로 받아온 DTO를 엔티티로 변화시켜준다.
			TodoEntity entity = TodoDTO.toEntity(dto);
			
			// (2) id를 null로 초기화한다. 생성 당시에는 id가 없고 이를 받아오기 때문이다.
			entity.setId(null);
			
			// (3) 임시 사용자 아이디를 설정해준다. 현재는 프론트가 없기 때문에 이를 받아올 곳이 없기에 이를 지역변수로 대체한다.
			entity.setUserId(temporaryUserId);
			
			// (4) 서비스를 이용해 Todo 엔티티를 생성한다.
			List<TodoEntity> entities = service.create(entity);
			
			// (5) 자바스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO 리스트로 변환한다.
			List<TodoDTO> dtoList = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
			
			// (6) 변환된 TodoDTO 리스트(dtoList)를 이용해 ResponseDTO를 초기화환다.
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtoList).build();
			
			// (7) ResponseDTO를 리턴한다.
			return ResponseEntity.ok().body(response);
			
		}catch(Exception e) {
			// (8) 만약 오류 메세지가 있다면 이를 담아 client에게 반환한다.
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
		
		// (5) 자바스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO 리스트로 변환한다.
		List<TodoDTO> dtoList = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
		
		// (6) 변환된 TodoDTO 리스트(dtoList)를 이용해 ResponseDTO를 초기화환다.
		ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtoList).build();
		
		// (7) ResponseDTO를 리턴한다.
		return ResponseEntity.ok().body(response);
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteTodo(@RequestBody TodoDTO dto){
		try {
			String temporaryUserId = "temporary-user";
			// (1) 인자로 받아온 DTO를 엔티티로 변화시켜준다.
			TodoEntity entity = TodoDTO.toEntity(dto);
			entity.setUserId(temporaryUserId);
			
			// (4) 서비스를 이용해 Todo 엔티티를 제거한다.
			List<TodoEntity> entities = service.delete(entity);
			
			// (5) 자바스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO 리스트로 변환한다.
			List<TodoDTO> dtoList = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
			
			// (6) 변환된 TodoDTO 리스트(dtoList)를 이용해 ResponseDTO를 초기화환다.
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtoList).build();
			
			// (7) ResponseDTO를 리턴한다.
			return ResponseEntity.ok().body(response);
			
		}catch(Exception e) {
			// (8) 만약 오류 메세지가 있다면 이를 담아 client에게 반환한다.
			String error = e.getMessage();
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	
	}
}
