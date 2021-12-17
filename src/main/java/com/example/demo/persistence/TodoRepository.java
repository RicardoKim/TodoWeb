package com.example.demo.persistence;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.TodoEntity;

@Repository // 이 어노테이션 또한 Component로 관리되는 객체임을 명시해주고 레포지토리라는 것을 말해준다.
public interface TodoRepository  extends JpaRepository<TodoEntity, String>{
	List<TodoEntity> findByUserId(String userId); //userId로 TodoEntity를 찾는 메소드를 정의하였다.
	/*
	 * JpaRepository<엔티티 클래스, 엔티티 기본타입>
	 * 엔티티 클래스는 테이블에 매핑될 엔티티 클래스이고 ID는 이 엔티티의 기본 키의 타입이다. 따라서 <TodoEntity, String>
	 * 으로 표현한것이다.
	 * 이러한 JpaRepository는 기본적인 데이터 베이스 오퍼레이션 인터페이스를 제공한다. 
	 * 
	 * 근데 어떻게 인터페이스를 가져와서 구현도 안 하고 쓸 수 있을까?
	 * 요건 질문해야지.....
	 */
}
