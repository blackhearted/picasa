package com.example.loader;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

public class PhotoActivity extends Activity {
	
	private String id;
	private String userName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_photo);	
	}
	
	@Override
	protected void onStart()
	{
		super.onStart();
		
		Intent intent = getIntent();
		
		id = intent.getStringExtra("ID");
		
		userName = intent.getStringExtra("User");
		
		new AlertDialog.Builder(this)
	    .setTitle("Album ID")
	    .setMessage( id + " : " + userName)	    
	    .show();
	}
	
}
