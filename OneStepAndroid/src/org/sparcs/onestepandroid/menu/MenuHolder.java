package org.sparcs.onestepandroid.menu;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.sparcs.onestepandroid.R;
import org.sparcs.onestepandroid.util.Values;

public class MenuHolder {
	public static ArrayList<MyMenu> menus;
	
	public MenuHolder() {
		menus = new ArrayList<MyMenu>();
		MyMenu menu;
		menu = new MyMenu(MyMenu.Type.SECTION_HEADER, "Ȩ", "home", R.drawable.home);
		menus.add(menu);
		menu = new MyMenu(MyMenu.Type.SECTION_HEADER, "����", "notice/student", R.drawable.notice);
		menus.add(menu);
		menu = new MyMenu(MyMenu.Type.SINGLE_LINE, "�л���������", "notice/student");
		menus.add(menu);
		menu = new MyMenu(MyMenu.Type.SINGLE_LINE, "���л�ȸ ����", "notice/usc");
		menus.add(menu);
		
		NameValuePair[] portal_boards = Values.INSTANCE.portalBoards;
		ArrayList<MyMenu> subMenus = new ArrayList<MyMenu>();
		for (NameValuePair portal : portal_boards) {
			menu = new MyMenu(MyMenu.Type.SINGLE_LINE, portal.getName(), "notice/" + portal.getValue());
			subMenus.add(menu);
		}
		menu = new MyMenu(MyMenu.Type.EXPANDABLE_SET, "�� ����", "notice/more", subMenus);
		menus.add(menu);
		menu = new MyMenu(MyMenu.Type.SECTION_HEADER, "����", "schedule", R.drawable.schedule);
		menus.add(menu);
		menu = new MyMenu(MyMenu.Type.SECTION_HEADER, "�п�������å","suggestion/main", R.drawable.student_suggestion);
		menus.add(menu);
		menu = new MyMenu(MyMenu.Type.SINGLE_LINE, "BEST", "suggestion/best");
		menus.add(menu);
		menu = new MyMenu(MyMenu.Type.SINGLE_LINE, "������", "suggestion/exam");
		menus.add(menu);
		menu = new MyMenu(MyMenu.Type.SINGLE_LINE, "������","suggestion/proc");
		menus.add(menu);
		menu = new MyMenu(MyMenu.Type.SINGLE_LINE, "��ü ���� ����", "suggestion/all");
		menus.add(menu);
		menu = new MyMenu(MyMenu.Type.SECTION_HEADER, "���ȫ��", "promotion", R.drawable.promotion);
		menus.add(menu);
		menu = new MyMenu(MyMenu.Type.SECTION_HEADER, "�ֿ����Ʈ ��ũ", "link", R.drawable.external_link);
		menus.add(menu);
		menu = new MyMenu(MyMenu.Type.SECTION_HEADER, "Q&A", "q&a", R.drawable.q_and_a);
		menus.add(menu);
		menu = new MyMenu(MyMenu.Type.SECTION_HEADER, "Vote/Survey", "vote", R.drawable.survey);
		menus.add(menu);
		menu = new MyMenu(MyMenu.Type.SECTION_HEADER, "����", "setting", R.drawable.settings);
		menus.add(menu);
		menu = new MyMenu(MyMenu.Type.SECTION_HEADER, "���л�ȸ �Ұ�", "about", R.drawable.introduction);
		menus.add(menu);
	}
}
