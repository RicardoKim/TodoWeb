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
			//�̰� request���� dto Ŭ������ ����Ǽ� ���°ž�?
			UserEntity user = UserEntity.builder()
					.email(userDTO.getEmail())
					.username(userDTO.getUsername())
					.password(passwordEncoder.encode(userDTO.getPassword()))
					.build();
			// Request -> DTO -> Entity -> DTO -> Response
			// �ٵ� �� ���� response�� ������ �ִ°���..? post method�� �׳� 200�� �൵ �Ǵ°� �ƴϾ�?
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
		// ��й�ȣ�� �´��� check
		UserEntity user = userService.checkPassword(userId, userDTO.getPassword(), passwordEncoder);
		
		if(user != null) {
			// service�� ���� ���� �Լ� ȣ��
			userService.delete(user);
			return ResponseEntity.ok().body(true);
		}else {
			ResponseDTO responseDTO =  ResponseDTO.builder().error("Password incorrect").build();
			return ResponseEntity.badRequest().body(responseDTO);
		}

	}
}
