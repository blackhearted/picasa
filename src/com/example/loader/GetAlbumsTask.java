package com.example.loader;

import java.util.ArrayList;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gdata.data.photos.AlbumEntry;
import com.google.gdata.data.photos.GphotoEntry;
import com.google.gdata.data.photos.UserFeed;

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
			activity.albums = getIds( picasaFetcher.getAlbumFeed(arg[0], "album"));				
			Log.d(ASYNC_TASK_LOG_PREFIX, "Got "+ activity.albums.size() + " albums");
		} catch (Exception e) {
			e.printStackTrace();
			Log.d(ASYNC_TASK_LOG_PREFIX, "fail");
		}
		return null;
	}

	private String getIdFromEntry(GphotoEntry<?> entry) {
		String entryID = entry.getId();
		String id = null;
		int idx = entryID.lastIndexOf("/");
		if( idx != -1 && idx != entryID.length() - 1){
			id = entryID.substring(idx+1);						
		}
		return id;
	}
	
	private ArrayList<String> getIds(UserFeed feed)
	{
		ArrayList<String> ret = new ArrayList<String>();
		Log.d(ASYNC_TASK_LOG_PREFIX, "Got "+ feed.getAlbumEntries().size() + " albums");
		if (!feed.getAlbumEntries().isEmpty()){
			for (AlbumEntry myAlbum : feed.getAlbumEntries()) {
				ret.add(myAlbum.getTitle().getPlainText());
			}
		}
		else if (!feed.getEntries().isEmpty()){
			Log.d(ASYNC_TASK_LOG_PREFIX, "Got "+ feed.getEntries().size() + " photoIDs");
			for (GphotoEntry<?> entry : feed.getEntries()) {
				String id = getIdFromEntry(entry);
				Log.d(ASYNC_TASK_LOG_PREFIX, "ID -> "+id);
				if (id != null)
					ret.add(id);
			}
		}
		return ret;
	}
	
	@Override
	protected void onPostExecute(Void result) {			
		Log.d(ASYNC_TASK_LOG_PREFIX, "onPostExecute...albums size = "+activity.albums.size());
		//adapter.notifyDataSetChanged();
		activity.adapter.addAll(activity.albums);
    }
}