package com.example.loader;

import java.io.IOException;
import java.net.URL;

import android.util.Log;

import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.data.photos.AlbumFeed;
import com.google.gdata.data.photos.UserFeed;
import com.google.gdata.util.ServiceException;

public class PicasaFetcher {
	
	private String APPLICATION_NAME = "pure-feat-393";
	
	private String baseUrl;
	private PicasawebService service;
	UserFeed feed;
	
	public PicasaFetcher(String url)
	{
		baseUrl = url;
		service = new PicasawebService(APPLICATION_NAME);
	}
	
	public UserFeed getAlbumFeed(String userName, String kind) throws IOException, ServiceException
	{
		URL feedUrl;
		feedUrl = new URL(baseUrl+"/"+userName+"?kind="+kind+"&access=public");
		
		Log.d("PicasaFetcher", "URL = " +feedUrl.toString());
		
		return service.getFeed(feedUrl, UserFeed.class);		
	}
	
	public AlbumFeed getPhotoFeed(String userName, String id) throws IOException, ServiceException
	{
		URL feedUrl;
		feedUrl = new URL(baseUrl+"/"+userName+"/ablumid/"+id);
		
		Log.d("PicasaFetcher", "URL = " +feedUrl.toString());
		
		return service.getFeed(feedUrl, AlbumFeed.class);		
	}
}
