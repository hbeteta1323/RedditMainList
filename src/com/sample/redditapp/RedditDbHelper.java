package com.sample.redditapp;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class RedditDbHelper extends SQLiteOpenHelper{

	// /data/data/com.sample.redditapp/reddit_db
	public final static String DATABASE_NAME = "reddit_db";
	public final static int DATABASE_VERSION=1;
	
	public static final String ITEM_CREATE =
			String.format("create table %s (%s int auto_increment,"
					      +"%s author text, %s thumbnail text,"
					      +"%s title text)", 
					      Tables.ITEM, ItemColumns._ID,
					      ItemColumns.AUTHOR,
					      ItemColumns.THUMBNAIL, ItemColumns.TITLE);
	
	public RedditDbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// Crear la BD	
		db.execSQL(ITEM_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

	public interface Tables {
		String ITEM = "ITEM";
	}
	
	public interface ItemColumns extends BaseColumns {
		String AUTHOR = "Author";
		String THUMBNAIL = "Thumbnail";
		String TITLE = "Title";
	}
}
