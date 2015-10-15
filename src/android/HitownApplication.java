package com.hitown.sdk2.manager;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.Application;

import com.hitown.sdk.manager.CrashHandler;

public class HitownApplication extends Application {
	private static String tag = "HitownApp";
	private HashMap<String, Activity> activitieList = new HashMap<String, Activity>();

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		// 注册自定义的崩溃异常处理函数
		CrashHandler crashHandler = CrashHandler.getInstance();
		crashHandler.init(this);
	}

	/** activity压栈 */
	public void pushApplication(String id, Activity activity) {
		activitieList.put(id, activity);
		System.out.println(tag + "::::::::activity入栈       " + "id-->" + id + "--->" + activity);
	}

	public int getcount() {
		return activitieList.size();
	}

	/** activity出栈 */
	public void pullApplication(String id) {
		System.out.println(tag + "::::::::activity出栈       " + "id-->" + id + "--->" + activitieList.get(id));
		activitieList.remove(id);
	}

	/** 退出系统 */
	public void exitSystem() {
		// 退出系统数据处理
		// WechatProvider.clearSystemCache();
		exitAllActivity();
		android.os.Process.killProcess(android.os.Process.myPid());
		System.exit(1);
	}

	/** 逆时间弹出所有activity */
	public void exitAllActivity() {
		ArrayList<String> ids = new ArrayList<String>();
		for (String id : activitieList.keySet()) {
			long key = Long.parseLong(id);
			if (ids.size() == 0)
				ids.add(id);
			else
				for (int i = 0; i < ids.size(); i++) {
					long l = Long.parseLong(ids.get(i));
					if (key > l) {
						ids.add(i, id);
						break;
					}
					if (i == ids.size() - 1) {
						ids.add(id);
						break;
					}
				}
		}
		for (String id : ids) {
			activitieList.get(id).finish();
			System.out.println(tag + "::::::::activity出栈       " + "id-->" + id + "--->" + activitieList.get(id));
		}

	}
}
