package com.sample.redditapp;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sample.redditapp.RedditDbHelper.ItemColumns;
import com.sample.redditapp.RedditDbHelper.Tables;

public class ItemDbManager {

	public static ItemDbManager instance;
	private Context context;
	
	public ItemDbManager(Context context){
		this.context=context;
	}
	
	public static ItemDbManager instance(Context context){
		if (instance==null){
			instance=new ItemDbManager(context);
		}
		if (instance.context!=context){
			instance.context=context;
		}
		return instance;
	}
	
	public boolean save(RedditItem item){
		RedditDbHelper helper=new RedditDbHelper(context);
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(ItemColumns.AUTHOR,item.author);
		values.put(ItemColumns.THUMBNAIL,item.thumbnail);
		values.put(ItemColumns.TITLE,item.title);
		long id = db.insert(Tables.ITEM, null, values);
		return id > 0;
	}

	public List<RedditItem> loadCachedData() {
		RedditDbHelper helper = new RedditDbHelper(context);
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.query(Tables.ITEM, null, null, null, null, null, null, null);
		List<RedditItem> items = new ArrayList<RedditItem>();
		while (cursor.moveToNext()){
			RedditItem item = new RedditItem();
			item.author=cursor.getString(cursor.getColumnIndex(ItemColumns.AUTHOR));
			item.thumbnail=cursor.getString(cursor.getColumnIndex(ItemColumns.THUMBNAIL));
			item.title=cursor.getString(cursor.getColumnIndex(ItemColumns.TITLE));
			items.add(item);
		}
		cursor.close();
		return items;
	}
	
	
}
