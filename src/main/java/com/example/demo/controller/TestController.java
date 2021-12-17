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
@RequestMapping("test") //자원 경로를 명시해주는 역할을 한다.
public class TestController {
	@GetMapping //HTTP의 GET메소드를 사용해서 접근하는 경우
	public String testController() {
		return "Hello World!"; //GET에 대한 Response로 Hello World라는 문자열을 반환해준다는 것을 의미한다.
	}
	
	@GetMapping("/testGetMapping") //HTPP의 GET메소드를 사용해서 접근하지만 해당 클래스의 자원 경로를 다음과 같이 더 추가할 수 있다.
	public String testControllerWithPath() {
		return "Hello World! testGetMapping";
	}
	
	//Client로 부터 요청 매개변수를 전달 받는 방법
	
	// 1. PathVariable를 이용하여 전달 받는 방법
	@GetMapping("/{id}") //URI의 경로로 넘어오는 매개변수 값을 id로 mapping하라는 의미이다.
	public String testControllerWithPathVariables(@PathVariable(required = false) int id) {
		//PathVariable을 통해 URI를 통해 받은 변수값을 정수형으로 전달해준다. 또한 required를 false로 함으로써 반드시 매개변수가
		//돌아올 필요는 없음을 명시한다.
		return "Hello World! ID " + id;
	}
	
	// 2. RequestParam을 이용하여 전달 받는 방법
	// 다음과 같이 class를 정의하면 클래스에서 명시된 자원 경로에 GetMapping으로 명시한 자원 경로에 더해서
	// 추가 파라미터를 사용하여 값을 전달 받을 수 있다.
	// ex) localhost:8080/test/testRequestParam?id=123
	@GetMapping("/testRequestParam")
	public String testControllerRequestParam(@RequestParam(required = false) int id) {
		return "Hello World! ID" + id;
	}
	
	// 3. RequestBody를 이용하여 전달 받는 방법
	// 반환하고자 하는 리소스가 복잡할때 사용하는 방법으로 오브젝트처럼 복잡한 자료형을 통째로 요청에 보내고 싶은 경우가 이에 해당한다.
	@GetMapping("/testRequestBody")
	public String testControllerRequestBody(@RequestBody TestRequestBodyDTO testRequestBodyDTO) {
		return "Hello World! ID " + testRequestBodyDTO.getId() + " Message : " + testRequestBodyDTO.getMessage();
	}
	
	//RequesBody로 보내오는 JSON을 TestRequestBodyDTO 오브젝트로 변환해 가져오라는 뜻이다.
	// 클라이언트는 요청 바디로 JSON형태의 문자열을 넘겨준다.
	// 이러한 JSON의 내부는 의미적으로 TestRequestBodyDTO와 같아야한다.
	
	
	// Response를 오브젝트로 반환해주는 경우
	@GetMapping("/testResponseBody")
	public ResponseDTO<String> testControllerResponseBody(){
		List<String> list = new ArrayList<>();
		list.add("Hello World! I'm ResponseDTO");
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
		return response;
	}
	
	// ResponseEntity는 우리가 응답의 body뿐만 아니라 status 또는 header를 조작하고 싶을 때 사용한다.
	@GetMapping("testResponseEntity")
	public ResponseEntity<?> testControllerResponseEntity(){
		List<String> list = new ArrayList<>();
		list.add("Hello World! I'm ResponseEntity. And you got 400!");
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
		return ResponseEntity.badRequest().body(response);
	}
}

