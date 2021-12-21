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
				.setSubject(userEntity.getId()) //토큰 제목
				.setIssuer("demo app") // 토큰 발급자
				.setIssuedAt(new Date()) // 토큰 발급 시간
				.setExpiration(expiryDate) // 토큰 만료 일자
				.compact();
	}
	
	public String validateAndGetUserId(String token) {
		/*
		 * token을 파싱하는 과정으로 parseClaimsJws(token)은 메소드가 토큰을 Base64로 디코딩 및 파싱 진행
		 * 이후 secret key를 이용해 위조 되었는지 확인
		 * 위조되지 않았다면 페이로드 리턴, 위조라면 예외를 남림
		 * 그럼 위조를 날렸을 때 처리하는 부분은?
		 * 페이로드란? name과 value의 쌍으로 되어있고 해당 부분에는 파싱을 한 정보가 들어가있다.
		 * 파싱 된 정보 중에 userId가 필요하므로 getBody를 부룬다.
		 */
		Claims claims = Jwts.parser()
				.setSigningKey(SECRET_KEY)
				.parseClaimsJws(token)
				.getBody();
		return claims.getSubject();
	}
}
