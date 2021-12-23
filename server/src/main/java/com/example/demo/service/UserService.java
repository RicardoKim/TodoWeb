package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.UserEntity;
import com.example.demo.persistence.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j // 자바에서 자동적으로 로그를 만들어주는 라이브러리이다.
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TodoService todoService;
	
	
	public UserEntity create(final UserEntity userEntity) {
		if(userEntity == null || userEntity.getEmail() == null) {
			throw new RuntimeException("Invalid arguments");
		}
		final String email = userEntity.getEmail();
		if(userRepository.existsById(email)) {
			log.warn("Email already exists{}", email);
			throw new RuntimeException("Email already exists");
		}
		
		return userRepository.save(userEntity);
	}
	
	public UserEntity getByCredentials(final String email, final String password, final PasswordEncoder encoder) {
		final UserEntity originalUser = userRepository.findByEmail(email);
		System.out.println(email);
		System.out.println(userRepository.findByEmail(email));
		if(originalUser != null && encoder.matches(password, originalUser.getPassword())) {
			return originalUser;
		}
		return null;
		
	}
	
	// userId와 password 대조
	public UserEntity checkPassword(final String userId, final String password, final PasswordEncoder encoder) {
		final Optional<UserEntity> original = userRepository.findById(userId);
		if(original.isPresent()) { //userId로 userEntity를 찾으면
			final UserEntity originalUser = original.get();
			if(originalUser != null && encoder.matches(password, originalUser.getPassword())) { // 받은 비밀번호와 대조
				return originalUser;
			}
		}
		// userId가 없거나 password가 맞지 않으면 null 반환
		return null;
	}
	
	// 계정 삭제 함수
	public void delete(final UserEntity userEntity) {
		
		if(userEntity.getId() != null) {
			// 계정 삭제 전 user의 Todo를 모두 삭제해야 함
			todoService.deleteAll(userEntity.getId());
			// 계정 삭제
			userRepository.delete(userEntity);
		}
		
	}
}
