package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.UserEntity;
import com.example.demo.persistence.TodoRepository;
import com.example.demo.persistence.UserRepository;
import com.example.demo.security.TokenProvider;
import com.example.demo.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/auth")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private TokenProvider tokenProvider;
	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO){
		try {
			//이게 request오면 dto 클래스에 포장되서 오는거야?
			UserEntity user = UserEntity.builder()
					.email(userDTO.getEmail())
					.username(userDTO.getUsername())
					.password(passwordEncoder.encode(userDTO.getPassword()))
					.build();
			// Request -> DTO -> Entity -> DTO -> Response
			// 근데 왜 굳이 response를 저렇게 주는거지..? post method면 그냥 200만 줘도 되는거 아니야?
			UserEntity registeredUser = userService.create(user);
			
			UserDTO responseUserDTO = UserDTO.builder()
					.email(registeredUser.getEmail())
					.id(registeredUser.getId())
					.username(registeredUser.getUsername())
					.build();
			System.out.println(responseUserDTO);
			return ResponseEntity.ok(responseUserDTO);
		}catch (Exception e) {
			ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
			return ResponseEntity.badRequest().body(responseDTO);
		}
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticate(@RequestBody UserDTO userDTO){
		System.out.println(userDTO.toString());
		UserEntity user = userService.getByCredentials(userDTO.getEmail(), userDTO.getPassword(), passwordEncoder);
		System.out.println(user);
		if(user != null) {
			final String token = tokenProvider.create(user);
			final UserDTO responseUserDTO = UserDTO.builder().email(user.getEmail()).id(user.getId()).token(token).build();
			return ResponseEntity.ok().body(responseUserDTO);
		}
		else {
			ResponseDTO responseDTO =  ResponseDTO.builder().error("Login failed").build();
			
			return ResponseEntity.badRequest().body(responseDTO);
		}
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteAccount(@AuthenticationPrincipal String userId, @RequestBody UserDTO userDTO){
		// 비밀번호가 맞는지 check
		UserEntity user = userService.checkPassword(userId, userDTO.getPassword(), passwordEncoder);
		
		if(user != null) {
			// service의 계정 삭제 함수 호출
			userService.delete(user);
			return ResponseEntity.ok().body(true);
		}else {
			ResponseDTO responseDTO =  ResponseDTO.builder().error("Password incorrect").build();
			return ResponseEntity.badRequest().body(responseDTO);
		}

	}
}
