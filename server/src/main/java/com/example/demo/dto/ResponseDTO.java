package com.example.demo.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder //디자인 패턴을 이야기 하는 부분으로 생성자의 매개변수의 순서에 상관 없이 클래스를 객체화 시킬 수 있다.
@NoArgsConstructor // 밑에서 사용한 클래스의 경우 매개변수가 존재하지 않는다. 즉 코드의 간결성에 좋은 이점을 가진다.
@AllArgsConstructor // 클래스 내부의 모든 변수가 매개변수로 받는 생성자임을 의미한다.
@Data // Getter와 Setter를 클래스 내부의 메소드로 정의하지 않아도 정의가 되게 해주는 어노테이션이다.
public class ResponseDTO<T> {
	private String error;
	private List<T> data;
}

/*
해당 DTO는 여러 모델의 DTO를 HTTP응답으로 반환하기 위한 DTO이다.
따라서 Java Generic을 활용해서 이를 구성하였다.
Java Generic이란 하나의 값이 여러 데이터 타입을 받을 수 있는 것을 의미한다.
무슨 뜻이나면 우리가 사용한 TodoDTO를 이용해서 Response를 하다가 다른 모델로 응답하고 싶을 수 있다.
이럴때를 위해 반환하는 데이터의 타입을 지정하지 않고 특정 값을 반환한다는 의미로 Generic을 사용하여 data를 지정했다. 
 
*/