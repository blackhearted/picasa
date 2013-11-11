package com.example.loader;

import java.util.ArrayList;
import java.util.HashMap;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class PhotoListActivity extends Activity {
	
	private String id;
	private String userName;
	private GetImagePropertiesTask getImagePropstask;
	private ArrayList<MyPhotoEntry> photoProperties = new ArrayList<MyPhotoEntry>(); 
	ListView list;
	LazyAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("PhotoListActivity", "onCreate ...");
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_photo);	
	}
	
	@Override
	protected void onStart()
	{
		Log.d("PhotoListActivity", "onStart ...");
		super.onStart();
		
		Intent intent = getIntent();
		
		id = intent.getStringExtra("ID");
		
		userName = intent.getStringExtra("User");
		
		new AlertDialog.Builder(this)
	    .setTitle("Album ID")
	    .setMessage( id + " : " + userName)	    
	    .show();
		
		getImagePropstask = new GetImagePropertiesTask(this);		
		getImagePropstask.execute(userName, id);	
		
		list=(ListView)findViewById(R.id.list);
		
		adapter=new LazyAdapter(this, photoProperties);
        list.setAdapter(adapter);
	}

	public ArrayList<MyPhotoEntry> getPhotoProperties() {
		Log.d("PhotoListActivity", "getPhotoProperties ...");
		return photoProperties;
	}

	public void setPhotoProperties(ArrayList<MyPhotoEntry> photoProperties) {
		Log.d("PhotoListActivity", "setPhotoProperties ...");
		this.photoProperties = photoProperties;
		adapter.setData(this.photoProperties);
		adapter.notifyDataSetChanged();
	}
	
}
