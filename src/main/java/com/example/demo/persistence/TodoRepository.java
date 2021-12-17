package com.example.demo.persistence;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.TodoEntity;

@Repository // �� ������̼� ���� Component�� �����Ǵ� ��ü���� ������ְ� �������丮��� ���� �����ش�.
public interface TodoRepository  extends JpaRepository<TodoEntity, String>{
	List<TodoEntity> findByUserId(String userId); //userId�� TodoEntity�� ã�� �޼ҵ带 �����Ͽ���.
	/*
	 * JpaRepository<��ƼƼ Ŭ����, ��ƼƼ �⺻Ÿ��>
	 * ��ƼƼ Ŭ������ ���̺� ���ε� ��ƼƼ Ŭ�����̰� ID�� �� ��ƼƼ�� �⺻ Ű�� Ÿ���̴�. ���� <TodoEntity, String>
	 * ���� ǥ���Ѱ��̴�.
	 * �̷��� JpaRepository�� �⺻���� ������ ���̽� ���۷��̼� �������̽��� �����Ѵ�. 
	 * 
	 * �ٵ� ��� �������̽��� �����ͼ� ������ �� �ϰ� �� �� ������?
	 * ��� �����ؾ���.....
	 */
}
