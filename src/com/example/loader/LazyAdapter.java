package com.example.loader;
 
import java.util.ArrayList;
import java.util.HashMap;

 
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class LazyAdapter extends BaseAdapter {
 
    private Activity activity;
    private static LayoutInflater inflater=null;
    private ArrayList<MyPhotoEntry> data = new ArrayList<MyPhotoEntry>();
    public ImageLoader imageLoader; 
 
 
    public LazyAdapter(Activity a, ArrayList<MyPhotoEntry> d) {
    	Log.d("LazyAdapter", ".ctor ...");
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
        imageLoader=new ImageLoader(activity.getApplicationContext());
    }
 
    public int getCount() {
        return data.size();
    }
 
    public Object getItem(int position) {
        return position;
    }
 
    public long getItemId(int position) {
        return position;
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
    	Log.d("LazyAdapter", "getView ...");
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.list_row, null);
 
        TextView title = (TextView)vi.findViewById(R.id.title); // title
        ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); // thumb image
        
        MyPhotoEntry entry = new MyPhotoEntry();
        entry = data.get(position);
        
        Log.d("LazyAdapter", "entry title =" + entry.title + " entry url = " + entry.url_full + " ... ");
        
        title.setText(entry.title);
        imageLoader.DisplayImage(entry.url_full, thumb_image);
        return vi;
    }
}