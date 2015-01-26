package com.pangff.networktesting;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.MultipartRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.ImageLoader.ImageListener;

public class ImageUploadActivity extends Activity {
	TextView textView1;
	ImageView imageView1;
	RequestQueue mRequestQueue;
	ImageLoader mImageLoader;
	ImageListener listener;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image_upload);
		mRequestQueue = Volley.newRequestQueue(this);
		textView1 = (TextView) findViewById(R.id.textView1);
		imageView1 = (ImageView) findViewById(R.id.imageView1);
		final File file = new File("/sdcard/Download/opengl1.jpg");
		listener = ImageLoader.getImageListener(imageView1, R.drawable.ic_launcher, R.drawable.ic_launcher);  
		final Map<String, String> mStringPart = new HashMap<String,String>();
		mImageLoader = new ImageLoader(mRequestQueue, new BitmapCache());
		textView1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MultipartRequest reuquest = new MultipartRequest("http://test1.ps.cn/raindropMh/terminal/terminalJson!uploadImage?portalId=3&userId=mh3_henzil&type=1", new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						Log.e("pangff", "error"+error.getMessage());
					}
				}, new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						Log.e("pangff", "onResponse:"+response.toString());
						try {
							mImageLoader.get(response.getString("msg"), listener);
						} catch (JSONException e) {
							e.printStackTrace();
						}  
					}
				}, file, "pushmessage",mStringPart);
				mRequestQueue.add(reuquest);
			}
		});
	}
}
