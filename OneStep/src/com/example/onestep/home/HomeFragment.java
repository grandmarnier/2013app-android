package com.example.onestep.home;

import java.util.ArrayList;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onestep.MainActivity.MainHandler;
import com.example.onestep.R;
import com.example.onestep.article.ArticleListInfo;
import com.example.onestep.util.NetworkManager;
import com.example.onestep.util.NetworkReturning;
import com.example.onestep.util.Values;
import com.example.onestep.util.XmlParser;

public class HomeFragment extends ListFragment {
	private HomeAdapter adapter;
	private ArrayList<HomeArticle> list;

	public HomeFragment() {
		list = new ArrayList<HomeArticle>();
		// �ӽ÷� ����Ʈ �ۼ�
		HomeArticle article;
		article = new HomeArticle(HomeArticle.Type.SECTION_HEADER, "�̺�Ʈ", null, null, 0);
		list.add(article);
		article = new HomeArticle(HomeArticle.Type.SINGLE_LINE, "�������� �̺�Ʈ�� �����ϴ�.", null, null, 0);
		list.add(article);
		article = new HomeArticle(HomeArticle.Type.SECTION_HEADER, "�п�������å", null, null, 0);
		list.add(article);
		article = new HomeArticle(HomeArticle.Type.SECONDARY_LINE, "���п�� �ݵ�� �������� �����ϵ��� ����", "1362034832000", "��â��", 2147483647);
		list.add(article);
		article = new HomeArticle(HomeArticle.Type.SECONDARY_LINE, "���п�� �ʿ����.", "1362034832000", "�̱�ȫ", 0);
		list.add(article);
		article = new HomeArticle(HomeArticle.Type.SECONDARY_LINE, "���ڸ� ������ʹ�.", "1362034832000", "������", 1818);
		list.add(article);
		article = new HomeArticle(HomeArticle.Type.SECONDARY_LINE, "���ڷ� �����ϴ� ���� ���� ���� ����", "1362034832000", "ä����", 231);
		list.add(article);
		article = new HomeArticle(HomeArticle.Type.SECONDARY_LINE, "���� �� ����.", "1362034832000", "�ֳ���", 1);
		list.add(article);
		article = new HomeArticle(HomeArticle.Type.SECTION_HEADER, "��������", null, null, 0);
		list.add(article);
		//		article = new HomeArticle(HomeArticle.Type.SECONDARY_LINE, "�̺�Ʈ", null, null, 0);
		//		list.add(article);
		//		article = new HomeArticle(HomeArticle.Type.SECONDARY_LINE, "�̺�Ʈ", null, null, 0);
		//		list.add(article);
		//		article = new HomeArticle(HomeArticle.Type.SECONDARY_LINE, "�̺�Ʈ", null, null, 0);
		//		list.add(article);
		//		article = new HomeArticle(HomeArticle.Type.SECONDARY_LINE, "�̺�Ʈ", null, null, 0);
		//		list.add(article);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final MainHandler handler = new MainHandler(getActivity());
		if (adapter == null)
			adapter = new HomeAdapter(getActivity(), list);
		setListAdapter(adapter);
		new Thread(new Runnable() {
			@Override
			public void run() {
				ArrayList<ArticleListInfo> parsed;
				NetworkReturning returning = NetworkManager.INSTANCE.getArticleList("student-notice", 1, 5, "portal");
				int status = returning.getStatus();
				
				if (status == 500) {
					parsed = new ArrayList<ArticleListInfo>();
				}
				else {
					if (status == 401) {
						SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(getActivity());
						NetworkManager.INSTANCE.login(
								preference.getString("username", ""), 
								preference.getString("password", ""));
						returning = NetworkManager.INSTANCE.getArticleList("student-notice", 1, 5, "portal");
					}
					XmlParser parser = new XmlParser();

					try {
						parsed = parser.parseArticleListInfo(returning.getResponse());
					} catch (Exception e) {
						e.printStackTrace();
						parsed = new ArrayList<ArticleListInfo>();
					} 
				}
				
				for (ArticleListInfo item : parsed) {
					HomeArticle article = new HomeArticle(
							HomeArticle.Type.SECONDARY_LINE,
							item.getTitle(), 
							item.getTime(), 
							item.getWriter(), 
							item.getHit());
					list.add(article);
				}
				try {
					getActivity().findViewById(R.id.content_frame).post(new Runnable() {
						@Override
						public void run() {
							getActivity().findViewById(R.id.content_frame).requestLayout();
							adapter.notifyDataSetChanged();
						}
					});
					Message msg = handler.obtainMessage();
					msg.arg1 = 1;
					handler.sendMessage(msg);
				}
				catch (Exception e) {
					Log.w(Values.INSTANCE.tag, e.toString());
				}
			}
		}).start();

		return inflater.inflate(R.layout.article_list, null);
	}

}
