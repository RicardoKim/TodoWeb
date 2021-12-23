package com.example.demo.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	@Autowired
	private TokenProvider tokenProvider;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
		try {
			// ������Ʈ���� ��ū ��������
			System.out.println(request);
			String token = parseBearerToken(request);
			log.info("Filter is running...");
			// ��ū �˻��ϱ�, JWT�̹Ƿ� ���⼭ ó�� ����
			if(token != null && !token.equalsIgnoreCase("null")) {
				//userId�� ���������� validateAndGetUserId�� ���� ���� ������ ���� ��ū�̶�� ����, �ƴϸ� ServletException�� �߻��Ѵ�.
				String userId = tokenProvider.validateAndGetUserId(token);
				log.info("Authenticated user ID : " + userId);
				// ����� ���� ���� ������ ������ �Ǵµ� �̷��� ���� Authentication�̶�� �մϴ�. �� �κ��� �̷��� Authentication�� �����ϴ� ������ �մϴ�.
				SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
				// �տ��� ����� ����� Authentication�� ����� �κ��Դϴ�.
				AbstractAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userId, //������ ����� ������ �־��ش�. 
						null, 
						AuthorityUtils.NO_AUTHORITIES
				);
				// ������ ������ ����� authentication�� ���� ���� ������ �����ϴ� �κ��Դϴ�.
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				// securityContext�� authendication�� �����ϴ� �κ��Դϴ�.
				securityContext.setAuthentication(authentication);
				// ���� ��ü�� ���� ������ �����Ͽ� �������α׷��� ���� ���� ���ؽ�Ʈ�� ���� ���� ������ �����ϴ� ���Դϴ�.
				// �̷��� SecurityContextHolder�� security context�� �����ϰ� �˴ϴ�.
				// ���� Authentication�� �����ϱ� ���ؼ��� SecurityContextHodler�� ���� SecurityContext�� �����Ͽ� Authentication�� �����ؾ��մϴ�.
				SecurityContextHolder.setContext(securityContext);
						
			}
		} catch (Exception ex) {
			logger.error("Could not set user authentication in sercurity context",ex);
		}
		
		filterChain.doFilter(request, response);
	}
	
	private String parseBearerToken(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}

}
