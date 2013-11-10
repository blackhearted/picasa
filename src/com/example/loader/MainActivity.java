package com.example.loader;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	public static final String PREFS_NAME = "MyPrefsFile";
	public static final String USER_NAME = "UserName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);
    	String userName = prefs.getString(USER_NAME, "");
    	EditText editText = (EditText)findViewById(R.id.userId);
    	editText.setText(userName);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void goClick (View v)
    {
    	// save user
    	SharedPreferences.Editor edit = getSharedPreferences(PREFS_NAME, 0).edit();
    	EditText editText = (EditText)findViewById(R.id.userId);
    	String editTextStr = editText.getText().toString();
    	edit.putString(USER_NAME, editTextStr);
    	edit.commit();
    	
    	//open new activity
    	Intent intent = new Intent(MainActivity.this, AlbumActivity.class);
    	intent.putExtra("User", editTextStr);
    	MainActivity.this.startActivity(intent);
    }
    
}
