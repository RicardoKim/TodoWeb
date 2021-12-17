package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDTO;
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
	
}
