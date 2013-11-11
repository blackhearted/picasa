package com.example.loader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;

class GetAlbumsTask extends
		AsyncTask<String, Void, Void > {
	/**
	 * 
	 */
	private final AlbumActivity activity;
	PicasaFetcher picasaFetcher = new PicasaFetcher("https://picasaweb.google.com/data/feed/api/user");

	/**
	 * @param albumActivity
	 */
	GetAlbumsTask(AlbumActivity albumActivity) {
		activity = albumActivity;
	}

	private static final String ASYNC_TASK_LOG_PREFIX = "async task";

	@Override
	protected void onPreExecute()
	{
		Log.d(ASYNC_TASK_LOG_PREFIX, "onPreExecute...albums size = "+activity.albums.size());
		activity.albums.clear();
		activity.adapter.clear();
		Log.d(ASYNC_TASK_LOG_PREFIX, "onPreExecute...albums size = "+activity.albums.size());
		activity.adapter.notifyDataSetChanged();
	}

	@Override
	protected Void doInBackground(String... arg) {
		try {
			HashMap<String, String> albumEntries = picasaFetcher.getAlbums(arg[0], "album");
			activity.albums = albumEntries;
			Log.d(ASYNC_TASK_LOG_PREFIX, "Got "+ activity.albums.size() + " albums");
		} catch (Exception e) {
			e.printStackTrace();
			Log.d(ASYNC_TASK_LOG_PREFIX, "fail");
		}
		return null;
	}	
	
	@Override
	protected void onPostExecute(Void result) {			
		Log.d(ASYNC_TASK_LOG_PREFIX, "onPostExecute...albums size = "+activity.albums.size());
		//adapter.notifyDataSetChanged();//??
		for(String title: activity.albums.keySet())
		{
			activity.adapter.add(title);
		}		
    }
}