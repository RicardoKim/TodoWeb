package com.example.demo.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.example.demo.model.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TokenProvider {
	private static final String SECRET_KEY = "1234";
	
	public String create(UserEntity userEntity) {
		Date expiryDate = Date.from(Instant.now().plus(1, ChronoUnit.DAYS));
		
		return Jwts.builder().signWith(SignatureAlgorithm.HS512, SECRET_KEY)
				.setSubject(userEntity.getId()) //��ū ����
				.setIssuer("demo app") // ��ū �߱���
				.setIssuedAt(new Date()) // ��ū �߱� �ð�
				.setExpiration(expiryDate) // ��ū ���� ����
				.compact();
	}
	
	public String validateAndGetUserId(String token) {
		/*
		 * token�� �Ľ��ϴ� �������� parseClaimsJws(token)�� �޼ҵ尡 ��ū�� Base64�� ���ڵ� �� �Ľ� ����
		 * ���� secret key�� �̿��� ���� �Ǿ����� Ȯ��
		 * �������� �ʾҴٸ� ���̷ε� ����, ������� ���ܸ� ����
		 * �׷� ������ ������ �� ó���ϴ� �κ���?
		 * ���̷ε��? name�� value�� ������ �Ǿ��ְ� �ش� �κп��� �Ľ��� �� ������ ���ִ�.
		 * �Ľ� �� ���� �߿� userId�� �ʿ��ϹǷ� getBody�� �η��.
		 */
		Claims claims = Jwts.parser()
				.setSigningKey(SECRET_KEY)
				.parseClaimsJws(token)
				.getBody();
		return claims.getSubject();
	}
}
