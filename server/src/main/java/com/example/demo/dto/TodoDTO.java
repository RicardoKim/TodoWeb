package com.example.demo.dto;

import com.example.demo.model.TodoEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder //디자인 패턴을 이야기 하는 부분으로 생성자의 매개변수의 순서에 상관 없이 클래스를 객체화 시킬 수 있다.
@NoArgsConstructor // 밑에서 사용한 클래스의 경우 매개변수가 존재하지 않는다. 즉 코드의 간결성에 좋은 이점을 가진다.
@AllArgsConstructor // 클래스 내부의 모든 변수가 매개변수로 받는 생성자임을 의미한다.
@Data // Getter와 Setter를 클래스 내부의 메소드로 정의하지 않아도 정의가 되게 해주는 어노테이션이다.
public class TodoDTO {
	private String id;
	private String title;
	private boolean done;
	
	public TodoDTO(final TodoEntity entity) {
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.done = entity.isDone();
	}
	/*
	 * Builder 패턴이라고 이야기 하는 패턴으로 우리가 AllargsContructor 어노테이션을 사용함으로써 생기는 문제상황을
	 * 방지하고자 하는 생성자 패턴이라고 이해를 했다.
	 * 왜나면 우리가 필드에서 선언한 모든 변수가 TodoEntity에 필요하지 않을 수 있다.
	 * 따라서 필요한 변수만을 사용하는 TodoDTO라는 메소드를 정의하고
	 * 해당 메소드를 바탕으로 TodoEntity를 정의한다. 이를 통해 원하는 변수만을 가지는 TodoDTO를 만들 수 있는 것이다.
	 */
	public static TodoEntity toEntity(final TodoDTO dto) {
		return TodoEntity.builder()
				.id(dto.getId())
				.title(dto.getTitle())
				.done(dto.isDone())
				.build();
	}
}


/*
DTO는 Data Transfer Object의 약자로 우리가 TodoEntity에 있는 정보를 반환해줄 때 사용하는 클래스이다.
그럼 왜 모델을 바로 반환하지 않고 DTO를 사용해서 모델을 반환할까?
모델은 데이터 테이블의 조직 및 데이터 구성과 거의 동일하다. 그렇기 때문에 모델 자체를 반환하는 것은 데이터 테이블에 대한 구조와 모델 등이 그대로
유출이 되는 위험성을 가질 수 있다. 따라서 이를 DTO처럼 다른 오브젝트로 변환하여 반환해주는 것이다.
두번째로는 모델이 소비자가 필요한 정보를 모두 담고 있지 않기 때문이다.
소비자는 단순히 우리의 데이터 테이블에 있는 정보만을 원하지 않을 수 있다. 예를 들어 사용자가 서비스를 이용하다가 오류를 접하게 되면 이러한 오류 또한
우리는 소비자한테 반환해줘야한다. 하지만 만약 모델만을 가지고 사용자한테 정보를 전달하기 위해서는 데이터테이블 상에 오류에 대한 Column을 추가해야할 것이다.
이는 매우 비효율적인 방식이기 때문에 DTO라는 오브젝트 내에 이를 포함하여 전달한다.

또한 TodoEntity와의 차이점 중 하나는 DTO에는 userId라는 것이 존재하지 않는 것이다. userId는 데이터 테이블 상에서 사용자의 정보를 보여주는 
중요한 값이다. 그리고 만약 토큰 방식의 로그인을 사용하면 사용자의 정보는 토큰화 되서 클라이언트에게 갈 것이기 때문에 서버는 이러한 사용자의 정보를
굳이 DTO에 추가해서 유출되게 할 위험을 가질 필요가 없다.
따라서 DTO에는 사용자의 정보인 usrId를 빼고 있는 것이다.
*/