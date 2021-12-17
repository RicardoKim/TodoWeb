package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.TestRequestBodyDTO;

@RestController
@RequestMapping("test") //�ڿ� ��θ� ������ִ� ������ �Ѵ�.
public class TestController {
	@GetMapping //HTTP�� GET�޼ҵ带 ����ؼ� �����ϴ� ���
	public String testController() {
		return "Hello World!"; //GET�� ���� Response�� Hello World��� ���ڿ��� ��ȯ���شٴ� ���� �ǹ��Ѵ�.
	}
	
	@GetMapping("/testGetMapping") //HTPP�� GET�޼ҵ带 ����ؼ� ���������� �ش� Ŭ������ �ڿ� ��θ� ������ ���� �� �߰��� �� �ִ�.
	public String testControllerWithPath() {
		return "Hello World! testGetMapping";
	}
	
	//Client�� ���� ��û �Ű������� ���� �޴� ���
	
	// 1. PathVariable�� �̿��Ͽ� ���� �޴� ���
	@GetMapping("/{id}") //URI�� ��η� �Ѿ���� �Ű����� ���� id�� mapping�϶�� �ǹ��̴�.
	public String testControllerWithPathVariables(@PathVariable(required = false) int id) {
		//PathVariable�� ���� URI�� ���� ���� �������� ���������� �������ش�. ���� required�� false�� �����ν� �ݵ�� �Ű�������
		//���ƿ� �ʿ�� ������ ����Ѵ�.
		return "Hello World! ID " + id;
	}
	
	// 2. RequestParam�� �̿��Ͽ� ���� �޴� ���
	// ������ ���� class�� �����ϸ� Ŭ�������� ��õ� �ڿ� ��ο� GetMapping���� ����� �ڿ� ��ο� ���ؼ�
	// �߰� �Ķ���͸� ����Ͽ� ���� ���� ���� �� �ִ�.
	// ex) localhost:8080/test/testRequestParam?id=123
	@GetMapping("/testRequestParam")
	public String testControllerRequestParam(@RequestParam(required = false) int id) {
		return "Hello World! ID" + id;
	}
	
	// 3. RequestBody�� �̿��Ͽ� ���� �޴� ���
	// ��ȯ�ϰ��� �ϴ� ���ҽ��� �����Ҷ� ����ϴ� ������� ������Ʈó�� ������ �ڷ����� ��°�� ��û�� ������ ���� ��찡 �̿� �ش��Ѵ�.
	@GetMapping("/testRequestBody")
	public String testControllerRequestBody(@RequestBody TestRequestBodyDTO testRequestBodyDTO) {
		return "Hello World! ID " + testRequestBodyDTO.getId() + " Message : " + testRequestBodyDTO.getMessage();
	}
	
	//RequesBody�� �������� JSON�� TestRequestBodyDTO ������Ʈ�� ��ȯ�� ��������� ���̴�.
	// Ŭ���̾�Ʈ�� ��û �ٵ�� JSON������ ���ڿ��� �Ѱ��ش�.
	// �̷��� JSON�� ���δ� �ǹ������� TestRequestBodyDTO�� ���ƾ��Ѵ�.
	
	
	// Response�� ������Ʈ�� ��ȯ���ִ� ���
	@GetMapping("/testResponseBody")
	public ResponseDTO<String> testControllerResponseBody(){
		List<String> list = new ArrayList<>();
		list.add("Hello World! I'm ResponseDTO");
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
		return response;
	}
	
	// ResponseEntity�� �츮�� ������ body�Ӹ� �ƴ϶� status �Ǵ� header�� �����ϰ� ���� �� ����Ѵ�.
	@GetMapping("testResponseEntity")
	public ResponseEntity<?> testControllerResponseEntity(){
		List<String> list = new ArrayList<>();
		list.add("Hello World! I'm ResponseEntity. And you got 400!");
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
		return ResponseEntity.badRequest().body(response);
	}
}

