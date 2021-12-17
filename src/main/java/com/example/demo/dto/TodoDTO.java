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
*/