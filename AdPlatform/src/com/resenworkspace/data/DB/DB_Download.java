package com.resenworkspace.data.DB;


import android.database.Cursor;

import com.resenworkspace.adplatform.XMLTAG;
import com.resenworkspace.data.XML.DB;
import com.resenworkspace.data.XML.MySQLiteOpenHelper;

public class DB_Download extends DB{
	public static String TABLE_DOWNLOAD = "Download";
    //Interface of DB tag
    public static String TABLE = TABLE_DOWNLOAD;    
    private static DB_Download sInstance = null;
    public synchronized static DB_Download getInstance(){
	   	 if(sInstance == null){
	   		 sInstance = new DB_Download();
	   	 }
	   	 return sInstance;
    }
    public static String DB_ID = "download_id";
    public static String DOWNLOADMAX  = "size";
    public static String DONWLOADSIZE = "size";
    
    public static int    INDEX_DB_ID            = 0;
    public static int    INDEX_ADPUB_ID         = 1;
    public static int    INDEX_DOWNLOAD_ID      = 2;
    public static int    INDEX_DOWNLOAD_NAME    = 3;
    public static int    INDEX_DOWNLOAD_DELAY   = 4;
    public static int    INDEX_DOWNLOAD_TYPE    = 5;
    public static int    INDEX_DOWNLOAD_PATH    = 6;
    public static int    INDEX_DOWNLOAD_MSG     = 7;
    public static int    INDEX_DOWNLOAD_SIZE    = 8;
    public static int    INDEX_DOWNLOAD_MAX     = 9;
    private static int   INDEX_DOWNLOAD_MAX_MAX = 9;
    public static String[] DOWNLOAD_INSERT = {
	   	 XMLTAG.AD_TAG,
	   	 XMLTAG.FILE.ITEM.ITEM_ID,
	   	 XMLTAG.FILE.ITEM.ITEM_NAME,
	   	 XMLTAG.FILE.ITEM.ITEM_DELAY,
	   	 XMLTAG.FILE.ITEM.ITEM_TYPE,
	   	 XMLTAG.FILE.ITEM.ITEM_PATH,
	   	 XMLTAG.FILE.ITEM.ITEM_MSG,
	   	 DONWLOADSIZE,
	   	 DOWNLOADMAX
    };
    public static String[] DOWNLOAD_CHECK = {
   		 DB_ID,
   		 XMLTAG.AD_TAG,
   		 XMLTAG.FILE.ITEM.ITEM_ID,
       	 XMLTAG.FILE.ITEM.ITEM_NAME,
       	 XMLTAG.FILE.ITEM.ITEM_DELAY,
       	 XMLTAG.FILE.ITEM.ITEM_TYPE,
       	 XMLTAG.FILE.ITEM.ITEM_PATH,
       	 XMLTAG.FILE.ITEM.ITEM_MSG,
	   	 DONWLOADSIZE,
	   	 DOWNLOADMAX    		 
    };
    public static  String[] FIELD_TYPE={
   	    "INTEGER PRIMARY KEY AUTOINCREMENT", 
   	    "text", 
   	    "text", 
   	    "text",
   	    "text",
   	    "text",
   	    "text",
   	    "text",
   	    "text",
   	    "text"
    };
    public boolean CheckDownloadExist(String AdPub,String FileId){
    	if(!DB_AdPub.getInstance().IsAdPubDBExist(AdPub))return false;
   	    Cursor c = getDownload(AdPub,FileId);
   	    return c.moveToFirst();
    }
    public boolean CheckDownloadExist(String AdPub){
    	if(!DB_AdPub.getInstance().IsAdPubDBExist(AdPub))return false;
   	    Cursor c = getDownload(AdPub);
   	    return c.moveToFirst();
    }     
    public Cursor getDownload(String AdPub){
    	if(!DB_AdPub.getInstance().IsAdPubDBExist(AdPub))return null;
   	 	String[] selectionArgs ={AdPub};
   	 	return MySQLiteOpenHelper.getInstance().select(TABLE_DOWNLOAD, DOWNLOAD_CHECK , XMLTAG.AD_TAG+"=?", selectionArgs, null, null, null);
    }
    public Cursor getDownload(String AdPub,String FileId){
    	if(!DB_AdPub.getInstance().IsAdPubDBExist(AdPub))return null;
   	 	String[] selectionArgs ={AdPub,FileId};
   	 	return MySQLiteOpenHelper.getInstance().select(TABLE_DOWNLOAD, DOWNLOAD_CHECK , XMLTAG.AD_TAG+"=? and "+XMLTAG.FILE.ITEM.ITEM_ID+"=?", selectionArgs, null, null, null);
    }
    
    public String getDownloadDBIdByAdPub(String AdPub,String FileId){
    	 if(!DB_AdPub.getInstance().IsAdPubDBExist(AdPub))return null;
	   	 Cursor c = getDownload(AdPub,FileId);
	   	 if(c.moveToFirst()){
	   		 return c.getString(INDEX_DB_ID);
	   	 }
	   	 return null;
    }
    public boolean setDownload(String[] download){
    	 if(download==null || download.length!=INDEX_DOWNLOAD_MAX_MAX)return false;
	   	 boolean resoult=false;
	   	 String AdPub  = download[0];
	   	 if(!DB_AdPub.getInstance().IsAdPubDBExist(AdPub))return false;
	   	 String FileId = download[1];
	   	 if(CheckDownloadExist(AdPub,FileId)){
	   		 if(MySQLiteOpenHelper.getInstance().update(TABLE_DOWNLOAD, DOWNLOAD_INSERT, download, DB_ID+"=?", new String []{getDownloadDBIdByAdPub(AdPub,FileId)+" "} )==1)resoult=true;
	   	 }else{
	   		 if(MySQLiteOpenHelper.getInstance().insert(TABLE_DOWNLOAD, DOWNLOAD_INSERT, download)==1)resoult=true;
	   	 }
	   	 notifyDownLoadChanged(AdPub,FileId);
	   	 return resoult;
    }
}
