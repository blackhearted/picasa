package com.example.loader;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
 
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
 
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
 
public class CustomizedListView extends Activity {
    ListView list;
    LazyAdapter adapter;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	Log.d("CustomizedListView", "onCreate ...");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
 
        ArrayList<MyPhotoEntry> photoEntries = new ArrayList<MyPhotoEntry>();
 
        list=(ListView)findViewById(R.id.list);
        
        Log.d("CustomizedListView", "found list...");
 
        // Getting adapter by passing xml data ArrayList
        adapter=new LazyAdapter(this, photoEntries);
        list.setAdapter(adapter);
        
        Log.d("CustomizedListView", "found list...");
 
        // Click event for single list row
        list.setOnItemClickListener(new OnItemClickListener() {
 
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
 
            }
        });
    }
}