package com.itheima_01;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
 * 	Map�ĵ�һ�ֱ�����ʽ��
 * 			�����ټ����е��ɷ�
 * 			�������е��ɷ�
 * 			��ȡÿһ���ɷ�
 * 			��ÿһ���ɷ�ȥ�����Լ���ϱ��
 */
public class MapDemo4 {
	public static void main(String[] args) {
		//����Map����
		Map<String,String> map = new HashMap<String,String>();
		//���ӳ���ϵ
		map.put("л�÷�", "�Ų�ֽ");
		map.put("�¹���", "����Ͱ");
		map.put("������", "����");
		//����Map����
		
		//�����ټ����е��ɷ�
		Set<String> keys = map.keySet();
		//�������е��ɷ�
		for (String key : keys) {
			//��ÿ���ɷ�ȥ�����Լ���ϱ���Ϳ�����
			String value = map.get(key);
			System.out.println("�ɷ�" + key + "---" + "ϱ����" + value);
		}
		
	}
}
