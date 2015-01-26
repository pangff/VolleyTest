package com.pangff.networktesting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MainActivity extends Activity {
	public static RequestQueue mRequestQueue;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mRequestQueue = Volley.newRequestQueue(this);
	}
	
	public void imageTest(View view){
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, ImageTestActivity.class);
		startActivity(intent);
	}
	
	public void mutiJsonTest(View view){
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, MutiJsonTestActivity.class);
		startActivity(intent);
	}
	
	public void imageUpload(View view){
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, ImageUploadActivity.class);
		startActivity(intent);
	}
	
	
}
