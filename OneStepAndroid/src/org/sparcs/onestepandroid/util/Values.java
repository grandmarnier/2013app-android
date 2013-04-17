package org.sparcs.onestepandroid.util;

import java.util.HashMap;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public enum Values {
	INSTANCE;
	public String host = "https://143.248.234.170/2013App";
	public NameValuePair[] portalBoards = new NameValuePair[] {
		new BasicNameValuePair("����/����/��","lecture-academic-paper"),
		new BasicNameValuePair("������/����/���","leadership-intern-counseling"),
		new BasicNameValuePair("��Ȱ��/����/����","dormitory-scholarship-welfare"),
		new BasicNameValuePair("�������� �ż�/����","academic-courses"),
		new BasicNameValuePair("���","recruiting"),
		new BasicNameValuePair("�л����Ƹ��Ұ�","student-club"),
		new BasicNameValuePair("�����������","researcher-on-military-duty"),
	};
	public HashMap<String, String> portalBoardsMap = new HashMap<String, String>();
	public String preferenceKey = "OneStep";
	public String tag = "com.example.onestep";
	private Values() {
		portalBoardsMap.put("student-notice", "�л���������");
		portalBoardsMap.put("gsc-usc-notice", "���л�ȸ ����");
		portalBoardsMap.put("lecture-academic-paper", "����/����/��");
		portalBoardsMap.put("leadership-intern-counseling", "������/����/���");
		portalBoardsMap.put("dormitory-scholarship-welfare", "��Ȱ��/����/����");
		portalBoardsMap.put("academic-courses", "�������� �ż�/����");
		portalBoardsMap.put("recruiting", "���");
		portalBoardsMap.put("student-club", "�л����Ƹ��Ұ�");
		portalBoardsMap.put("researcher-on-military-duty", "�����������");
	}
}
