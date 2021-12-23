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

@Builder //������ ������ �̾߱� �ϴ� �κ����� �������� �Ű������� ������ ��� ���� Ŭ������ ��üȭ ��ų �� �ִ�.
@NoArgsConstructor // �ؿ��� ����� Ŭ������ ��� �Ű������� �������� �ʴ´�. �� �ڵ��� ���Ἲ�� ���� ������ ������.
@AllArgsConstructor // Ŭ���� ������ ��� ������ �Ű������� �޴� ���������� �ǹ��Ѵ�.
@Data // Getter�� Setter�� Ŭ���� ������ �޼ҵ�� �������� �ʾƵ� ���ǰ� �ǰ� ���ִ� ������̼��̴�.
@Entity // ���� Ŭ������ Entity�� ��Ÿ���� Ŭ���� ���� �������� ������̼��̴�.
@Table(name = "Todo") 
/*
 * ���̺� �������ִ� ������̼����� �� ��ƼƼ�� �����ͺ��̽��� Todo ���̺� ���εȴٴ� ���̴�. 
 * ���� �߰����� ������ @Entity�� �̸��� ���̺� �̸����� �����Ѵ�.
 */

public class TodoEntity {
	//@Id�� ������̼� �ؿ� �ִ� ������ ��ƼƼ�� ���������� ������ִ� �����̴�.
	@Id
	//ID�� �ڵ����� �������ִ� ������ �Ѵ�. ���⼭ �ָ��ؾ��ϴ� ���� �ڵ����� ������ � ������ �����ϳ��̴�. �츮�� system-uuid���
	//generator�� �����ؼ�����Ѵٴ� ���̴�.
	@GeneratedValue(generator = "system-uuid")
	// �ս� ������ generator�� ���ؼ� ��üȭ ���ִ� �κ����� uuid�� �������� ����Ǵ� id�� ������ִ� ǥ�� �Ծ��̴�.
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id; // �� ������Ʈ�� ���̵� , �� �����ͺ��̽��� ���� ���̶�� ������ ���.
	private String userId; // �� ������Ʈ�� ������ ������� ���̵�
	private String title; // Todo Ÿ��Ʋ
	private boolean done; // true - todo�� �Ϸ��� ���
}

/*
Entity�� ����Ͻ� �����͸� ��� ���Ұ� ������ ���̽��� ���̺�� ��Ű���� ǥ���ϴ� �� ������ �Ѵ�.
���̶�� ���� ���� �����ϰ��� �ϴ� ������ �м��ϰ�, �� �м��� ����� ����Ǵ� ���� �ǹ��Ѵ�.
���� ��� �츮�� Todo list�� �ۼ��ϰ��� �� �� ���� ����ڸ� �������ִ� ������ ���̵�, �ش� Todo�� ���� ������� ���̵�, �׸��� �� Todo�� Ÿ��Ʋ
���������� �ش� Todo�� �Ϸ�Ǿ����� üũ�ϱ� ���� done���� �̷��� 4���� ���� �ʿ��ϴٴ� ���� ���� �ߴٰ� �����غ���.
�׷��� ������ �츮�� �ڵ忡���� �� 4���� ���� ������ ��ƼƼ�� ������ٰ� ������ �� �ִ�.
�׷� ��ƼƼ�� ����? �ս� ���� �������� ���� �����̶�� �̾߱� �� �� �ִ�.
���� �̷��� Ŭ������ ���� ������ �츮�� ������ �������� ���� �ʿ�� �ϴ� ������ � ��ƼƼ��� �������� ���� ���� ���Ǿ��ٰ� �� �� ���� ���̴�.
*/


/*����
Entity�� ������ ���̺� ���� ǥ���� ���ִ� Ŭ�����̴�.
Repository�� Entity�� �������� ������ �����ϴ� Ŭ�����̴�.
*/