package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder //디자인 패턴을 이야기 하는 부분으로 생성자의 매개변수의 순서에 상관 없이 클래스를 객체화 시킬 수 있다.
@NoArgsConstructor // 밑에서 사용한 클래스의 경우 매개변수가 존재하지 않는다. 즉 코드의 간결성에 좋은 이점을 가진다.
@AllArgsConstructor // 클래스 내부의 모든 변수가 매개변수로 받는 생성자임을 의미한다.
@Data // Getter와 Setter를 클래스 내부의 메소드로 정의하지 않아도 정의가 되게 해주는 어노테이션이다.
@Entity // 현재 클래스가 Entity를 나타내는 클래스 임을 알져누는 어노테이션이다.
@Table(name = "Todo") 
/*
 * 테이블 지정해주는 어노테이션으로 이 엔티티는 데이터베이스의 Todo 테이블에 매핑된다는 뜻이다. 
 * 만약 추가하지 않으면 @Entity의 이름을 테이블 이름으로 간주한다.
 */

public class TodoEntity {
	//@Id는 어노테이션 밑에 있는 변수가 엔티티의 고유값임을 명시해주는 역할이다.
	@Id
	//ID를 자동으로 생성해주는 역할을 한다. 여기서 주목해야하는 점은 자동으로 생성을 어떤 식으로 진행하냐이다. 우리는 system-uuid라는
	//generator를 참조해서사용한다는 뜻이다.
	@GeneratedValue(generator = "system-uuid")
	// 앞써 정의한 generator에 대해서 구체화 해주는 부분으로 uuid는 고유성이 보장되는 id를 만들어주는 표준 규약이다.
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id; // 이 오브젝트의 아이디 , 즉 데이터베이스의 고유 값이라고 생각이 든다.
	private String userId; // 이 오브젝트를 생성한 사용자의 아이디
	private String title; // Todo 타이틀
	private boolean done; // true - todo를 완료한 경우
}

/*
Entity는 비즈니스 데이터를 담는 역할과 데이터 베이스의 테이블과 스키마를 표현하는 두 역할을 한다.
모델이라는 것은 내가 개발하고자 하는 영역을 분석하고, 그 분석의 결과로 도출되는 것을 의미한다.
예를 들어 우리가 Todo list를 작성하고자 할 때 먼저 사용자를 구별해주는 고유한 아이디, 해당 Todo를 만든 사용자의 아이디, 그리고 그 Todo의 타이틀
마지막으로 해당 Todo가 완료되었는지 체크하기 위한 done까지 이렇게 4개의 값이 필요하다는 것을 도출 했다고 가정해보자.
그렇기 때문에 우리의 코드에서는 저 4개의 값을 가지고 엔티티를 만들었다고 생각할 수 있다.
그럼 엔티티는 뭘까? 앞써 말한 정보들을 모은 집합이라고 이야기 할 수 있다.
따라서 이러한 클래스를 만든 이유는 우리가 설계한 도메인의 모델이 필요로 하는 정보를 어떤 엔티티라는 집합으로 묶기 위해 사용되었다고 할 수 있을 것이다.
*/


/*질문
Entity는 데이터 테이블에 대한 표현을 해주는 클래스이다.
Repository는 Entity를 바탕으로 정보를 추출하는 클래스이다.
*/