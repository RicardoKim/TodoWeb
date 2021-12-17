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
	
}
