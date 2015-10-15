package com.hitown.sdk2.manager;


import android.app.TabActivity;
import android.os.Bundle;

/**
 * 请勿修改移除该类。（可自行增加必要功能）
 * 
 * @author judy
 * 
 * 
 */
public class HitownTabActivity extends TabActivity {
	private String id = System.currentTimeMillis() + "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		((HitownApplication) getApplication()).pushApplication(id, this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		((HitownApplication) getApplication()).pullApplication(id);
	}

}
