package com.pangff.networktesting;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

public class ImageTestActivity extends Activity {
	
	ImageView imageView1;
	ImageView imageView2;
	Button textView1;
	Button textView2;
	Button textView3;
	ImageLoader mImageLoader;
	ImageListener listener;
	NetworkImageView networkImageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image_test);
		
		RequestQueue mRequestQueue = Volley.newRequestQueue(this);
		mImageLoader = new ImageLoader(mRequestQueue, new BitmapCache());
		imageView1 = (ImageView) findViewById(R.id.imageView1);
		imageView2 = (ImageView) findViewById(R.id.imageView2);
		textView1 = (Button) findViewById(R.id.textView1);
		textView2 = (Button) findViewById(R.id.textView2);
		textView3 = (Button) findViewById(R.id.textView3);
		listener = ImageLoader.getImageListener(imageView1,  
		        R.drawable.ic_launcher, R.drawable.ic_launcher);  
		
		networkImageView = (NetworkImageView) findViewById(R.id.imageView3);
		
		textView1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mImageLoader.get("http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg", listener);  
			}
		});
		
		textView3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				networkImageView.setDefaultImageResId(R.drawable.ic_launcher);  
				networkImageView.setErrorImageResId(R.drawable.ic_launcher);
				networkImageView.setImageUrl("http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg", mImageLoader);
			}
		});
	}
	
}
