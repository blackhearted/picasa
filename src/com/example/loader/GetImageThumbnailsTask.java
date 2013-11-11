package com.example.loader;
import com.google.gdata.data.photos.AlbumEntry;
import com.google.gdata.data.photos.AlbumFeed;
import com.google.gdata.data.photos.GphotoEntry;
import com.google.gdata.data.photos.PhotoEntry;
import com.google.gdata.data.photos.UserFeed;

import android.os.AsyncTask;
import android.util.Log;


class GetImageThumbnailsTask extends
		AsyncTask<String, Void, Void > {
	/**
	 * 
	 */
	private final PhotoActivity activity;
	PicasaFetcher picasaFetcher = new PicasaFetcher("https://picasaweb.google.com/data/feed/api/user");

	/**
	 * @param albumActivity
	 */
	GetImageThumbnailsTask(PhotoActivity photoActivity) {
		activity = photoActivity;
	}

	private static final String ASYNC_TASK_LOG_PREFIX = "thumb task";

	@Override
	protected void onPreExecute()
	{
		Log.d(ASYNC_TASK_LOG_PREFIX, "onPreExecute...");		
	}

	@Override
	protected Void doInBackground(String... arg) {
		try {		
			Log.d(ASYNC_TASK_LOG_PREFIX, "doInBackground");
			AlbumFeed feed = picasaFetcher.getPhotoFeed(arg[0], arg[1]);
			
			if (!feed.getEntries().isEmpty()){
				Log.d(ASYNC_TASK_LOG_PREFIX, feed.getPhotoEntries().size() + " photos...");
				for (GphotoEntry<?> entry : feed.getEntries()) {
					Log.d(ASYNC_TASK_LOG_PREFIX, "entry -> " + entry.getId());
					//ret.add(myAlbum.getTitle().getPlainText());
				}
			}
			else{
				Log.d(ASYNC_TASK_LOG_PREFIX, "no photos...");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.d(ASYNC_TASK_LOG_PREFIX, "fail");
		}
		return null;
	}
	
	@Override
	protected void onPostExecute(Void result) {			
		Log.d(ASYNC_TASK_LOG_PREFIX, "onPostExecute...");
    }
}
