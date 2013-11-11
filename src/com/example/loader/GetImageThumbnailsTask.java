package com.example.loader;
import java.util.HashMap;

import com.example.loader.PicasaFetcher.MyPhotoEntry;
import com.google.gdata.data.photos.AlbumEntry;
import com.google.gdata.data.photos.AlbumFeed;
import com.google.gdata.data.photos.GphotoEntry;
import com.google.gdata.data.photos.PhotoEntry;
import com.google.gdata.data.photos.UserFeed;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;


class GetImageThumbnailsTask extends
		AsyncTask<String, Void, Bitmap > {
	/**
	 * 
	 */
	private final PhotoListActivity activity;
	PicasaFetcher picasaFetcher = new PicasaFetcher("https://picasaweb.google.com/data/feed/api/user");

	/**
	 * @param albumActivity
	 */
	GetImageThumbnailsTask(PhotoListActivity photoActivity) {
		activity = photoActivity;
	}

	private static final String ASYNC_TASK_LOG_PREFIX = "thumb task";

	@Override
	protected void onPreExecute()
	{
		Log.d(ASYNC_TASK_LOG_PREFIX, "onPreExecute...");		
	}

	@Override
	protected Bitmap doInBackground(String... arg) {
		try {		
			Log.d(ASYNC_TASK_LOG_PREFIX, "doInBackground");
			HashMap<String, MyPhotoEntry> photoEntries = picasaFetcher.getPhotoFeed(arg[0], arg[1]);
			Log.d(ASYNC_TASK_LOG_PREFIX, "no photos...");
			
		} catch (Exception e) {
			e.printStackTrace();
			Log.d(ASYNC_TASK_LOG_PREFIX, "fail");
		}
		return null;
	}
	
	@Override
	protected void onPostExecute(Bitmap result) {			
		Log.d(ASYNC_TASK_LOG_PREFIX, "onPostExecute...");
    }
	
	private Bitmap dowloadImage(String url)
	{
		return null;
	}
}
