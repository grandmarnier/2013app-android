package com.example.onestep.home;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v4.app.ListFragment;
import android.util.AttributeSet;
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
	private Context context;
	private ArrayList<HomeArticle> list;

	public HomeFragment() {
		list = new ArrayList<HomeArticle>();
		// 임시로 리스트 작성
		HomeArticle article;
		article = new HomeArticle(HomeArticle.Type.SECTION_HEADER, "이벤트", null, null, 0);
		list.add(article);
		article = new HomeArticle(HomeArticle.Type.SINGLE_LINE, "진행중인 이벤트가 없습니다.", null, null, 0);
		list.add(article);
		article = new HomeArticle(HomeArticle.Type.SECTION_HEADER, "학우제안정책", null, null, 0);
		list.add(article);
		article = new HomeArticle(HomeArticle.Type.SECONDARY_LINE, "여학우는 반드시 전산학을 전공하도록 하자", "1362034832000", "정창제", 2147483647);
		list.add(article);
		article = new HomeArticle(HomeArticle.Type.SECONDARY_LINE, "여학우는 필요없다.", "1362034832000", "이근홍", 0);
		list.add(article);
		article = new HomeArticle(HomeArticle.Type.SECONDARY_LINE, "여자를 만나고싶다.", "1362034832000", "김정민", 1818);
		list.add(article);
		article = new HomeArticle(HomeArticle.Type.SECONDARY_LINE, "여자로 시작하는 말은 여우 여시 여행", "1362034832000", "채종욱", 231);
		list.add(article);
		article = new HomeArticle(HomeArticle.Type.SECONDARY_LINE, "적을 말 없다.", "1362034832000", "최낙현", 1);
		list.add(article);
		article = new HomeArticle(HomeArticle.Type.SECTION_HEADER, "공지사항", null, null, 0);
		list.add(article);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i("fragment", "onCreateView");
		final View view = inflater.inflate(R.layout.article_list, null);
		if (adapter == null)
			adapter = new HomeAdapter(getActivity(), list);
		setListAdapter(adapter);
		adapter.notifyDataSetChanged();
		return view;
	}


	@Override
	public void onResume() {
		Log.i("fragment", "onResume");
		// TODO Auto-generated method stub
		super.onResume();
		final MainHandler handler = new MainHandler(getActivity());
		SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(getActivity());
		System.out.println(preference.getString("username", ""));
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
						try {
						SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(getActivity());
						NetworkManager.INSTANCE.login(
								preference.getString("username", ""), 
								preference.getString("password", ""));
						}
						catch (Exception e) {
							e.printStackTrace();
						}
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

				final ArrayList<ArticleListInfo>finalParsed = parsed;
				try {
					getView().post(new Runnable() {
						@Override
						public void run() {
							for (ArticleListInfo item : finalParsed) {
								HomeArticle article = new HomeArticle(
										HomeArticle.Type.SECONDARY_LINE,
										item.getTitle(), 
										item.getTime(), 
										item.getWriter(), 
										item.getHit());
								list.add(article);
							}
							adapter.notifyDataSetChanged();
						}
					});
				}
				catch (Exception e) {
					e.printStackTrace();
					//Log.w(Values.INSTANCE.tag, e.toString());
				}
				Message msg = handler.obtainMessage();
				msg.arg1 = 1;
				handler.sendMessage(msg);
			}
		}).start();
	}



}
