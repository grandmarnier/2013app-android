package com.example.onestep.util;

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
	public String preferenceKey = "OneStep";
}
