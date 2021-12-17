package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder //������ ������ �̾߱� �ϴ� �κ����� �������� �Ű������� ������ ��� ���� Ŭ������ ��üȭ ��ų �� �ִ�.
@NoArgsConstructor // �ؿ��� ����� Ŭ������ ��� �Ű������� �������� �ʴ´�. �� �ڵ��� ���Ἲ�� ���� ������ ������.
@AllArgsConstructor // Ŭ���� ������ ��� ������ �Ű������� �޴� ���������� �ǹ��Ѵ�.
@Data // Getter�� Setter�� Ŭ���� ������ �޼ҵ�� �������� �ʾƵ� ���ǰ� �ǰ� ���ִ� ������̼��̴�.
public class TodoEntitity {
	private String id; // �� ������Ʈ�� ���̵� , �� �����ͺ��̽��� ���� ���̶�� ������ ���.
	private String userId; // �� ������Ʈ�� ������ ������� ���̵�
	private String title; // Todo Ÿ��Ʋ
	private boolean done; // true - todo�� �Ϸ��� ���
}
