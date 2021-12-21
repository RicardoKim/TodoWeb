package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class UserEntity {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	
	@Column(nullable = false)
	private String username;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String password;
}


/* 해당 클래스가 필요한 이유 
 * 프로젝트에는 두가지의 Entity가 존재한다. 첫번 째는 사용자의 정보이고 두번 째는 이러한 정보를 바탕으로 불러온 Todo list이다.
 * Q) 왜 서로 다른 테이블을 만들까?
 * 물론 TodoEntity도 사용자 식별을 위해 UserId가 들어간다. 하지만 UserId만 존재한다. 개인 정보에는 이러한 Id이외에도 email과 password가 존재한다.
 * 따라서 모든 TodoEntity에 사용자의 정보를 매 행 마다 넣을 수 없기 때문에 개인정보를 위한 테이블이 따로 존재하고 이를 위한 Entity가 존재하는 것이다.
 * */
