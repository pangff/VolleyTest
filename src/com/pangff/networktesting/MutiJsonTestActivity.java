package com.pangff.networktesting;

import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class MutiJsonTestActivity extends Activity {

	Button textView1;
	TextView contentView1;
	Button textView2;
	TextView contentView2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mutijson_test);
		
		textView1 = (Button) findViewById(R.id.textView1);
		contentView1 = (TextView) findViewById(R.id.contentView1);
		textView1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				doVolleyRequest();
			}
		});
	}

	private void doVolleyRequest() {
		for(int i=0;i<100;i++){
			final int index = i;
			JsonObjectRequest jsonObjectRequest = null;
			if(i==1){
				jsonObjectRequest = new JsonObjectRequest(
						"http://www.baiduaaaaa.com?time="+i, null,
						new Response.Listener<JSONObject>() {
							@Override
							public void onResponse(JSONObject response) {
								Log.d("TAG", response.toString());
								contentView1.setText("完成请求"+index+":"+response.toString());
							}
						}, new Response.ErrorListener() {
							@Override
							public void onErrorResponse(VolleyError error) {
								Log.e("TAG", error.getMessage(), error);
								contentView1.setText("错误请求"+index+":"+error.getMessage());
							}
						});
			}else{
				jsonObjectRequest = new JsonObjectRequest(
						"http://220.181.47.36/youguu/newRank/vip_concludes", null,
						new Response.Listener<JSONObject>() {
							@Override
							public void onResponse(JSONObject response) {
								Log.d("TAG", response.toString());
								contentView1.setText("完成请求"+index+":"+response.toString());
							}
						}, new Response.ErrorListener() {
							@Override
							public void onErrorResponse(VolleyError error) {
								Log.e("TAG", error.getMessage(), error);
								contentView1.setText("错误请求"+index+":"+error.getMessage());
							}
						});
			}
			jsonObjectRequest.setTag("123");
			MainActivity.mRequestQueue.add(jsonObjectRequest);
		}
		
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		MainActivity.mRequestQueue.cancelAll("123");
	}

}
