package com.example.loader;
import java.util.ArrayList;
import java.util.HashMap;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;


class GetImagePropertiesTask extends
		AsyncTask<String, Void, ArrayList<MyPhotoEntry> > {
	/**
	 * 
	 */
	private final PhotoListActivity activity;
	PicasaFetcher picasaFetcher = new PicasaFetcher("https://picasaweb.google.com/data/feed/api/user");

	/**
	 * @param albumActivity
	 */
	GetImagePropertiesTask(PhotoListActivity photoActivity) {
		activity = photoActivity;
	}

	private static final String ASYNC_TASK_LOG_PREFIX = "GetImagePropertiesTask";

	@Override
	protected void onPreExecute()
	{
		Log.d(ASYNC_TASK_LOG_PREFIX, "onPreExecute...");		
	}

	@Override
	protected ArrayList<MyPhotoEntry> doInBackground(String... arg) {
		try {		
			Log.d(ASYNC_TASK_LOG_PREFIX, "doInBackground");
			return picasaFetcher.getPhotoFeed(arg[0], arg[1]);
		} catch (Exception e) {
			e.printStackTrace();
			Log.d(ASYNC_TASK_LOG_PREFIX, "fail");
		}
		return null;
	}
	
	@Override
	protected void onPostExecute(ArrayList<MyPhotoEntry> result) {			
		Log.d(ASYNC_TASK_LOG_PREFIX, "onPostExecute...");
		Log.d(ASYNC_TASK_LOG_PREFIX, "got -> " + result.size() + " entries...");
		activity.setPhotoProperties(result);
    }
}
