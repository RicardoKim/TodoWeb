package com.example.demo.dto;

import com.example.demo.model.TodoEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder //������ ������ �̾߱� �ϴ� �κ����� �������� �Ű������� ������ ��� ���� Ŭ������ ��üȭ ��ų �� �ִ�.
@NoArgsConstructor // �ؿ��� ����� Ŭ������ ��� �Ű������� �������� �ʴ´�. �� �ڵ��� ���Ἲ�� ���� ������ ������.
@AllArgsConstructor // Ŭ���� ������ ��� ������ �Ű������� �޴� ���������� �ǹ��Ѵ�.
@Data // Getter�� Setter�� Ŭ���� ������ �޼ҵ�� �������� �ʾƵ� ���ǰ� �ǰ� ���ִ� ������̼��̴�.
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
	 * Builder �����̶�� �̾߱� �ϴ� �������� �츮�� AllargsContructor ������̼��� ��������ν� ����� ������Ȳ��
	 * �����ϰ��� �ϴ� ������ �����̶�� ���ظ� �ߴ�.
	 * �ֳ��� �츮�� �ʵ忡�� ������ ��� ������ TodoEntity�� �ʿ����� ���� �� �ִ�.
	 * ���� �ʿ��� �������� ����ϴ� TodoDTO��� �޼ҵ带 �����ϰ�
	 * �ش� �޼ҵ带 �������� TodoEntity�� �����Ѵ�. �̸� ���� ���ϴ� �������� ������ TodoDTO�� ���� �� �ִ� ���̴�.
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
DTO�� Data Transfer Object�� ���ڷ� �츮�� TodoEntity�� �ִ� ������ ��ȯ���� �� ����ϴ� Ŭ�����̴�.
�׷� �� ���� �ٷ� ��ȯ���� �ʰ� DTO�� ����ؼ� ���� ��ȯ�ұ�?
���� ������ ���̺��� ���� �� ������ ������ ���� �����ϴ�. �׷��� ������ �� ��ü�� ��ȯ�ϴ� ���� ������ ���̺� ���� ������ �� ���� �״��
������ �Ǵ� ���輺�� ���� �� �ִ�. ���� �̸� DTOó�� �ٸ� ������Ʈ�� ��ȯ�Ͽ� ��ȯ���ִ� ���̴�.
�ι�°�δ� ���� �Һ��ڰ� �ʿ��� ������ ��� ��� ���� �ʱ� �����̴�.
�Һ��ڴ� �ܼ��� �츮�� ������ ���̺� �ִ� �������� ������ ���� �� �ִ�. ���� ��� ����ڰ� ���񽺸� �̿��ϴٰ� ������ ���ϰ� �Ǹ� �̷��� ���� ����
�츮�� �Һ������� ��ȯ������Ѵ�. ������ ���� �𵨸��� ������ ��������� ������ �����ϱ� ���ؼ��� ���������̺� �� ������ ���� Column�� �߰��ؾ��� ���̴�.
�̴� �ſ� ��ȿ������ ����̱� ������ DTO��� ������Ʈ ���� �̸� �����Ͽ� �����Ѵ�.

���� TodoEntity���� ������ �� �ϳ��� DTO���� userId��� ���� �������� �ʴ� ���̴�. userId�� ������ ���̺� �󿡼� ������� ������ �����ִ� 
�߿��� ���̴�. �׸��� ���� ��ū ����� �α����� ����ϸ� ������� ������ ��ūȭ �Ǽ� Ŭ���̾�Ʈ���� �� ���̱� ������ ������ �̷��� ������� ������
���� DTO�� �߰��ؼ� ����ǰ� �� ������ ���� �ʿ䰡 ����.
���� DTO���� ������� ������ usrId�� ���� �ִ� ���̴�.
*/