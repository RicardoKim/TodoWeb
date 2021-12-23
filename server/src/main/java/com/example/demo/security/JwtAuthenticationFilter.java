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
			// 리퀘스트에서 토큰 가져오기
			System.out.println(request);
			String token = parseBearerToken(request);
			log.info("Filter is running...");
			// 토큰 검사하기, JWT이므로 여기서 처리 가능
			if(token != null && !token.equalsIgnoreCase("null")) {
				//userId가 정상적으로 validateAndGetUserId로 부터 전달 받으면 정상 토큰이라고 생각, 아니면 ServletException이 발생한다.
				String userId = tokenProvider.validateAndGetUserId(token);
				log.info("Authenticated user ID : " + userId);
				// 사용자 마다 접근 권한을 가지게 되는데 이러한 것을 Authentication이라고 합니다. 이 부분은 이러한 Authentication을 보관하는 역할을 합니다.
				SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
				// 앞에서 언급한 사용자 Authentication을 만드는 부분입니다.
				AbstractAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userId, //인증된 사용자 정보를 넣어준다. 
						null, 
						AuthorityUtils.NO_AUTHORITIES
				);
				// 다음은 위에서 언급한 authentication에 대한 세부 정보를 저장하는 부분입니다.
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				// securityContext에 authendication을 저장하는 부분입니다.
				securityContext.setAuthentication(authentication);
				// 보안 주체의 세부 정보를 포함하여 응용프로그램의 현재 보안 컨텍스트에 대한 세부 정보를 저장하는 곳입니다.
				// 이러한 SecurityContextHolder는 security context를 보관하게 됩니다.
				// 따라서 Authentication에 접근하기 위해서는 SecurityContextHodler를 통해 SecurityContext에 접근하여 Authentication에 접근해야합니다.
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
