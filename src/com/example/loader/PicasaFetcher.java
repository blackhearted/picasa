package com.example.loader;


import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.client.HttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class PicasaFetcher {
	private String APPLICATION_NAME = "pure-feat-393";
	
	private String baseUrl;
	JSONParser jsonParser = new JSONParser();
	
	public PicasaFetcher(String url)
	{
		baseUrl = url;
	}
	
	public HashMap<String, String> getAlbums(String userName, String kind) throws IOException, URISyntaxException, JSONException
	{
		URL feedUrl;
		feedUrl = new URL(baseUrl+"/"+userName+"?kind="+kind+"&access=public&alt=json");
		
		Log.d("PicasaFetcher", "URL = " +feedUrl.toString());
		
		return getAlbums(jsonParser.getJSONFromUrl(feedUrl.toString()));		
	}
	
	public ArrayList<MyPhotoEntry> getPhotoFeed(String userName, String id) throws IOException
	{
		URL feedUrl;
		feedUrl = new URL(baseUrl+"/"+userName+"/albumid/"+id+"/?alt=json");
		
		Log.d("PicasaFetcher", "URL = " +feedUrl.toString());
		return getPhotosProperties(jsonParser.getJSONFromUrl(feedUrl.toString()));		
	}
	
	private HashMap<String, String> getAlbums(JSONObject json)
	{
		HashMap<String, String> ret = new HashMap<String, String>();
		
		try {
			JSONObject feed = json.getJSONObject("feed");
			Log.d("PicasaFetcher", "got feed");
			JSONArray entries = feed.getJSONArray("entry");
			Log.d("PicasaFetcher", "got "+ entries.length() + " entries...");
			for(int i=0; i < entries.length(); ++i)
			{
				JSONObject entry = entries.getJSONObject(i);
				JSONObject idObj = entry.getJSONObject("gphoto$id");
				String id = idObj.getString("$t");
				JSONObject titleObj = entry.getJSONObject("title");
				String title = titleObj.getString("$t");
				ret.put(title, id);				
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}	
		
		return ret;
	}
	
	private ArrayList<MyPhotoEntry> getPhotosProperties(JSONObject json)
	{
		ArrayList<MyPhotoEntry> ret = new ArrayList<MyPhotoEntry>();
		
		try {
			JSONObject feed = json.getJSONObject("feed");
			Log.d("PicasaFetcher", "got feed");
			JSONArray entries = feed.getJSONArray("entry");
			Log.d("PicasaFetcher", "got "+ entries.length() + " entries...");
			for(int i=0; i < entries.length(); ++i)
			{
				JSONObject entry = entries.getJSONObject(i);
				
				JSONObject idObj = entry.getJSONObject("gphoto$id");
				String id = idObj.getString("$t");
				
				JSONObject titleObj = entry.getJSONObject("title");
				String title = titleObj.getString("$t");
				
				JSONObject mediaObj = entry.getJSONObject("media$group");
				JSONArray mediaContentArray = mediaObj.getJSONArray("media$content");
				String pictureUrl = "";
				if (mediaContentArray.length() > 0)
				{
					Log.d("PicasaFetcher", "got "+ mediaContentArray.length() + " images...");
					pictureUrl = mediaContentArray.getJSONObject(0).getString("url");
					Log.d("PicasaFetcher", "picture  -> "+ pictureUrl);
				}
				
				JSONArray mediaThumbnailArray = mediaObj.getJSONArray("media$thumbnail");				
				if (mediaThumbnailArray.length() > 0)
				{
					Log.d("PicasaFetcher", "got "+ mediaThumbnailArray.length() + " thumbnails...");
					for(int i1=0; i1< mediaThumbnailArray.length(); ++i1)
					{
						String thumbUrl = mediaThumbnailArray.getJSONObject(i1).getString("url");
						Log.d("PicasaFetcher", "thumb  -> "+ thumbUrl);
					}
				}
				MyPhotoEntry photoEntry = new MyPhotoEntry();
				photoEntry.title = title;
				photoEntry.url_full = pictureUrl;
				photoEntry.id = id;
				
				ret.add(photoEntry);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}	
		
		return ret;
	}
	
}
