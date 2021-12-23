package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.UserEntity;
import com.example.demo.persistence.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j // �ڹٿ��� �ڵ������� �α׸� ������ִ� ���̺귯���̴�.
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
	
	// userId�� password ����
	public UserEntity checkPassword(final String userId, final String password, final PasswordEncoder encoder) {
		final Optional<UserEntity> original = userRepository.findById(userId);
		if(original.isPresent()) { //userId�� userEntity�� ã����
			final UserEntity originalUser = original.get();
			if(originalUser != null && encoder.matches(password, originalUser.getPassword())) { // ���� ��й�ȣ�� ����
				return originalUser;
			}
		}
		// userId�� ���ų� password�� ���� ������ null ��ȯ
		return null;
	}
	
	// ���� ���� �Լ�
	public void delete(final UserEntity userEntity) {
		
		if(userEntity.getId() != null) {
			// ���� ���� �� user�� Todo�� ��� �����ؾ� ��
			todoService.deleteAll(userEntity.getId());
			// ���� ����
			userRepository.delete(userEntity);
		}
		
	}
}
