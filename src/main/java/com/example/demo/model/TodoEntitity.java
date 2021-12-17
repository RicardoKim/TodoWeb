package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder //디자인 패턴을 이야기 하는 부분으로 생성자의 매개변수의 순서에 상관 없이 클래스를 객체화 시킬 수 있다.
@NoArgsConstructor // 밑에서 사용한 클래스의 경우 매개변수가 존재하지 않는다. 즉 코드의 간결성에 좋은 이점을 가진다.
@AllArgsConstructor // 클래스 내부의 모든 변수가 매개변수로 받는 생성자임을 의미한다.
@Data // Getter와 Setter를 클래스 내부의 메소드로 정의하지 않아도 정의가 되게 해주는 어노테이션이다.
public class TodoEntitity {
	private String id;
	private String userId;
	private String title;
}
