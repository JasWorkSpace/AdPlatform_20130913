package com.resenworkspace.data.DB;

import android.content.Context;

import com.resenworkspace.data.XML.AdPlatformDataBaseHelper;
import com.resenworkspace.data.XML.MySQLiteOpenHelper;

public class DBManager {
    
	private static DBManager   sInstance;
	public  static void Init(Context context){
		sInstance = new DBManager(context);
    }
	public static DBManager getInstance(){
		return sInstance;
	}
	public DBManager(Context context){
		AdPlatformDataBaseHelper.Init(context);
	}
}
