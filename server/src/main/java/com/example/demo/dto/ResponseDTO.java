package com.example.demo.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder //������ ������ �̾߱� �ϴ� �κ����� �������� �Ű������� ������ ��� ���� Ŭ������ ��üȭ ��ų �� �ִ�.
@NoArgsConstructor // �ؿ��� ����� Ŭ������ ��� �Ű������� �������� �ʴ´�. �� �ڵ��� ���Ἲ�� ���� ������ ������.
@AllArgsConstructor // Ŭ���� ������ ��� ������ �Ű������� �޴� ���������� �ǹ��Ѵ�.
@Data // Getter�� Setter�� Ŭ���� ������ �޼ҵ�� �������� �ʾƵ� ���ǰ� �ǰ� ���ִ� ������̼��̴�.
public class ResponseDTO<T> {
	private String error;
	private List<T> data;
}

/*
�ش� DTO�� ���� ���� DTO�� HTTP�������� ��ȯ�ϱ� ���� DTO�̴�.
���� Java Generic�� Ȱ���ؼ� �̸� �����Ͽ���.
Java Generic�̶� �ϳ��� ���� ���� ������ Ÿ���� ���� �� �ִ� ���� �ǹ��Ѵ�.
���� ���̳��� �츮�� ����� TodoDTO�� �̿��ؼ� Response�� �ϴٰ� �ٸ� �𵨷� �����ϰ� ���� �� �ִ�.
�̷����� ���� ��ȯ�ϴ� �������� Ÿ���� �������� �ʰ� Ư�� ���� ��ȯ�Ѵٴ� �ǹ̷� Generic�� ����Ͽ� data�� �����ߴ�. 
 
*/