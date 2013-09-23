package com.resenworkspace.data.DB;

import android.database.Cursor;

import com.resenworkspace.adplatform.XMLTAG;
import com.resenworkspace.data.XML.MySQLiteOpenHelper;

public class DB_Option {

	public static String TABLE_OPTION = XMLTAG.OPTION.OPTION_TAG;
    //Interface of DB tag
    public static String TABLE = TABLE_OPTION;    
    private static DB_Option sInstance = null;
    synchronized static DB_Option getInstance(){
	   	 if(sInstance == null){
	   		 sInstance = new DB_Option();
	   	 }
	   	 return sInstance;
    }
    public static String DB_ID = "option_id";
    public static int  INDEX_DB_ID            = 0;
    public static int  INDEX_ADPUB_ID         = 1;
    public static int  INDEX_OPTION_BKIMG_TAG = 2;
    public static int  INDEX_OPTION_BKIMG_ID  = 3;
    private static int INDEX_OPTION_MAX       = 3;
    public static String[] OPTION_INSERT = {
	   	 XMLTAG.AD_TAG,
	   	 XMLTAG.OPTION.BKIMG.BKIMG_TAG,
	   	 XMLTAG.OPTION.BKIMG.BKIMG_ID
   };
   public static String[] OPTION_CHECK = {
		 DB_ID,
		 XMLTAG.AD_TAG,
		 XMLTAG.OPTION.BKIMG.BKIMG_TAG, 	
		 XMLTAG.OPTION.BKIMG.BKIMG_ID
   };
   public static  String[] OPTION_TYPE={
	    "INTEGER PRIMARY KEY AUTOINCREMENT", 
	    "text", 
	    "text",
	    "text"
   };
   public Cursor getAdPubOption(String Adpub){
	   if(!DB_AdPub.CheckAdPubAviable(Adpub))return null;
	   String[] selectionArgs ={Adpub};
  	   return MySQLiteOpenHelper.getInstance().select(TABLE_OPTION, OPTION_CHECK , XMLTAG.AD_TAG+"=?", selectionArgs, null, null, null);
   }
   private boolean IsAdPubOptionExist(String Adpub){
	   if(!DB_AdPub.CheckAdPubAviable(Adpub))return false;
	   Cursor c = getAdPubOption(Adpub);
	   return c.moveToFirst();
   }
   private String getAdPubOptionIdByAdPub(String adPub){
	   	 Cursor c = getAdPubOption(adPub);
	   	 if(c.moveToFirst()){
	   		 return c.getString(INDEX_DB_ID);
	   	 }
	   	 return null;
   } 
   public boolean setOption(String[] option){
	   	 if(option==null || option.length != INDEX_OPTION_MAX)return false;
	   	 boolean resoult=false;
	   	 String adPub = option[0];
	   	 if(DB_AdPub.CheckAdPubAviable(adPub))return false;
	   	 if(IsAdPubOptionExist(adPub)){
	   		 if(MySQLiteOpenHelper.getInstance().update(TABLE_OPTION, OPTION_INSERT, option, DB_ID+"=?", new String []{getAdPubOptionIdByAdPub(adPub)+" "} )==1)resoult=true;
	   	 }else{
	   		 if(MySQLiteOpenHelper.getInstance().insert(TABLE_OPTION, OPTION_INSERT, option)==1)resoult=true;
	   	 }
	   	 return resoult;
  } 
  
  public boolean DelOption(String adPub){
		boolean resoult=true;
		if(IsAdPubOptionExist(adPub)){		
			if(MySQLiteOpenHelper.getInstance().delete(TABLE_OPTION, DB_ID+"=?", new String []{getAdPubOptionIdByAdPub(adPub)+" "} )==1)resoult=true;
		}
		return resoult;
  }
}
