package com.resenworkspace.data.DB;

import android.database.Cursor;

import com.resenworkspace.adplatform.XMLTAG;
import com.resenworkspace.data.XML.DB;
import com.resenworkspace.data.XML.MySQLiteOpenHelper;

public class DB_File extends DB{
	 
	 
     public static String TABLE_FILE = XMLTAG.FILE.FILE_TAG;
     //Interface of DB tag
     public static String TABLE = TABLE_FILE;
     private static DB_File sInstance = null;
     synchronized static DB_File getInstance(){
    	 if(sInstance == null){
    		 sInstance = new DB_File();
    	 }
    	 return sInstance;
     }
     
     public static String DB_ID = "file_id";
     public static int    INDEX_DB_ID      = 0;
     public static int    INDEX_ADPUB_ID   = 1;
     public static int    INDEX_FILE_ID    = 2;
     public static int    INDEX_FILE_NAME  = 3;
     public static int    INDEX_FILE_DELAY = 4;
     public static int    INDEX_FILE_TYPE  = 5;
     public static int    INDEX_FILE_PATH  = 6;
     public static int    INDEX_FILE_MSG   = 7;
     private static int   INDEX_FILE_MAX   = 7;
     
     public static String[] FILE_INSERT = {
    	 XMLTAG.AD_TAG,
    	 XMLTAG.FILE.ITEM.ITEM_ID,
    	 XMLTAG.FILE.ITEM.ITEM_NAME,
    	 XMLTAG.FILE.ITEM.ITEM_DELAY,
    	 XMLTAG.FILE.ITEM.ITEM_TYPE,
    	 XMLTAG.FILE.ITEM.ITEM_PATH,
    	 XMLTAG.FILE.ITEM.ITEM_MSG
     };
     public static String[] FILE_CHECK = {
    		 DB_ID,
    		 XMLTAG.AD_TAG,
    		 XMLTAG.FILE.ITEM.ITEM_ID,
        	 XMLTAG.FILE.ITEM.ITEM_NAME,
        	 XMLTAG.FILE.ITEM.ITEM_DELAY,
        	 XMLTAG.FILE.ITEM.ITEM_TYPE,
        	 XMLTAG.FILE.ITEM.ITEM_PATH,
        	 XMLTAG.FILE.ITEM.ITEM_MSG    		 
     };
     public static  String[] FIELD_TYPE={
    	    "INTEGER PRIMARY KEY AUTOINCREMENT", 
    	    "text", 
    	    "text", 
    	    "text",
    	    "text",
    	    "text",
    	    "text",
    	    "text"
     };
     public boolean CheckFileExist(String FileId){
    	 Cursor c = getFile(FileId);
    	 return c.moveToFirst();
     }
     public Cursor getFile(){
    	 return MySQLiteOpenHelper.getInstance().select(TABLE_FILE, FILE_CHECK , null, null, null, null, null);
     }
     
     public Cursor getFile(String FileId){
    	 String[] selectionArgs ={FileId};
    	 return MySQLiteOpenHelper.getInstance().select(TABLE_FILE, FILE_CHECK , XMLTAG.FILE.ITEM.ITEM_NAME+"=?", selectionArgs, null, null, null);
     }
     public String getFileDBIdByID(String FileId){
    	 Cursor c = getFile();
    	 if(c.moveToFirst()){
    		 return c.getString(INDEX_DB_ID);
    	 }
    	 return null;
     }
     //depand FILE_INSERT
     public boolean setFile(String[] File){
    	 if(File==null || File.length<INDEX_FILE_MAX)return false;
    	 boolean resoult=false;
    	 String AdPub  = File[0];
    	 if(!DB_AdPub.CheckAdPubAviable(AdPub))return false;
    	 String FileId = File[1];
    	 if(CheckAdPubFileExist(AdPub,FileId)){
    		 if(MySQLiteOpenHelper.getInstance().update(TABLE_FILE, FILE_INSERT, File, DB_ID+"=?", new String []{getFileDBIdByIDAndAdPub(AdPub,FileId)+" "} )==1)resoult=true;
    	 }else{
    		 if(MySQLiteOpenHelper.getInstance().insert(TABLE_FILE, FILE_INSERT, File)==1)resoult=true;
    	 }
    	 notifyDBChanged(FileId);
    	 return resoult;
     }
     public Cursor getFile(String AdPub,String FileId){
    	 if(DB_AdPub.CheckAdPubAviable(AdPub))return null;
    	 String[] selectionArgs ={AdPub,FileId};
    	 return MySQLiteOpenHelper.getInstance().select(TABLE_FILE, FILE_CHECK , XMLTAG.AD_TAG+"=? and "+XMLTAG.FILE.ITEM.ITEM_NAME+"=?", selectionArgs, null, null, null);
     }
     private String getFileDBIdByIDAndAdPub(String AdPub,String FileId){
    	 Cursor c = getFile(AdPub,FileId);
    	 if(c.moveToFirst()){
    		 return c.getString(INDEX_DB_ID);
    	 }
    	 return null;
     } 
     private boolean CheckAdPubFileExist(String AdPub , String FileId){
    	 Cursor c = getFile(AdPub,FileId);
    	 return c.moveToFirst();
     }
}
