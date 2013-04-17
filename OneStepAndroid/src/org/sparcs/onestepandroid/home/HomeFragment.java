package org.sparcs.onestepandroid.home;

import java.util.ArrayList;

import org.sparcs.onestepandroid.MainActivity.MainHandler;
import org.sparcs.onestepandroid.R;
import org.sparcs.onestepandroid.article.ArticleListInfo;
import org.sparcs.onestepandroid.util.NetworkManager;
import org.sparcs.onestepandroid.util.NetworkReturning;
import org.sparcs.onestepandroid.util.ThreadManager;
import org.sparcs.onestepandroid.util.XmlParser;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends ListFragment {
	private Context context;
	private ArrayList<HomeArticle> list;
	public HomeFragment() {
		super();

	}
	public static HomeFragment newInstance(Context context) {
		HomeFragment instance = new HomeFragment();
		instance.setRetainInstance(true);
		instance.initialize(context);
		return instance;
	}

	public void initialize(Context context) {
		this.context = context;
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
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i("fragment", "onCreateView");
		return inflater.inflate(R.layout.article_list, null);
	}
	@Override
	public void onResume() {
		super.onResume();
		final MainHandler handler = new MainHandler(getActivity());
		final SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				Message msg = handler.obtainMessage();
				msg.arg1 = 0;
				handler.sendMessage(msg);

				ArrayList<ArticleListInfo> parsed = null;
				NetworkReturning returning = NetworkManager.INSTANCE.getArticleList("student-notice", 1, 5, "portal");
				int status = returning.getStatus();
				if (status == 500) {
					parsed = new ArrayList<ArticleListInfo>();
				}
				else {
					if (status == 401) {
						NetworkManager.INSTANCE.login(
								preference.getString("username", ""), 
								preference.getString("password", ""));
						returning = NetworkManager.INSTANCE.getArticleList("student-notice", 1, 5, "portal");
						status = returning.getStatus();
					}
					if (status == 200) {
						XmlParser parser = new XmlParser();
						try {
							parsed = parser.parseArticleListInfo(returning.getResponse());
						} catch (Exception e) {
							e.printStackTrace();
							parsed = new ArrayList<ArticleListInfo>();
						} 
					}
					else 
						parsed = new ArrayList<ArticleListInfo>();
				}

				final ArrayList<ArticleListInfo> finalParsed = parsed;
				Handler uiHandler = new Handler(Looper.getMainLooper()) {
					@Override
					public void handleMessage(Message msg) {
						for (ArticleListInfo item : finalParsed) {
							HomeArticle article = new HomeArticle(
									HomeArticle.Type.SECONDARY_LINE,
									item.getTitle(), 
									item.getTime(), 
									item.getWriter(), 
									item.getHit());
							list.add(article);
						}
						HomeAdapter adapter = new HomeAdapter(context, list);
						setListAdapter(adapter);
					}

				};
				uiHandler.sendEmptyMessage(0);
				msg = handler.obtainMessage();
				msg.arg1 = 1;
				handler.sendMessage(msg);
			}
		};
		ThreadManager.INSTANCE.getPoolExcecutor().execute(runnable);

		Log.i("fragment", "onCreatViewEnd");
	}

}
