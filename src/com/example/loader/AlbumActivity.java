package com.example.loader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.loader.util.SystemUiHider;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class AlbumActivity extends Activity {
		
	private String userName = "";
	
	ListView listView;
	
	ArrayAdapter<String> adapter = null;
	HashMap<String, String> albums = new HashMap<String, String>();
	
	GetAlbumsTask mt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_fullscreen);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		Intent intent = getIntent();
		String value = intent.getStringExtra("User");
		
		userName = value;
		
		new AlertDialog.Builder(this)
	    .setTitle("User Name")
	    .setMessage( value)	    
	    .show();	
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
		listView = (ListView) findViewById(R.id.listView);
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			
			private static final String LIST_CLICK_LOG_PREFIX = "list click";
			
			@Override
			public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
				String selectedFromList =(String) (listView.getItemAtPosition(myItemInt));
				
				Log.d(LIST_CLICK_LOG_PREFIX, "Got "+ selectedFromList + " clicked");
				
				Intent intent = new Intent(AlbumActivity.this, PhotoListActivity.class);
		    	intent.putExtra("User", userName);
		    	intent.putExtra("ID", albums.get(selectedFromList));
		    	AlbumActivity.this.startActivity(intent);
			}
		});	
	}
	
	public void GetAlbumIds(View v) throws IOException
	{	
		mt = new GetAlbumsTask(this);		
		mt.execute(userName);		
	}
}
