package com.resenworkspace.data.DB;

import android.database.Cursor;

import com.resenworkspace.adplatform.XMLTAG;
import com.resenworkspace.data.Download.DownloadUtils;
import com.resenworkspace.data.XML.DB;
import com.resenworkspace.data.XML.MySQLiteOpenHelper;

public class DB_AdPub extends DB{
     
	public static String TABLE_AD = XMLTAG.AD_TAG;
    //Interface of DB tag
    public static String TABLE = TABLE_AD;    
    private static DB_AdPub sInstance = null;
    synchronized static DB_AdPub getInstance(){
	   	 if(sInstance == null){
	   		 sInstance = new DB_AdPub();
	   	 }
	   	 return sInstance;
    }
    public static final String DOWNLOAD_STATE   = "state";
    
    public static String DB_ID = "adpub_id";
    public static int  INDEX_DB_ID       = 0;
    public static int  INDEX_ADPUB_ID    = 1;
    public static int  INDEX_ADPUB_STATE = 2;
    private static int INDEX_ADPUB_MAX   = 2;
    public static String[] ADPUB_INSERT = {
	   	 XMLTAG.AD_TAG,
	   	 DOWNLOAD_STATE
    };
    public static String[] ADPUB_CHECK = {
		 DB_ID,
		 XMLTAG.AD_TAG,
		 DOWNLOAD_STATE 		 
    };
    public static  String[] ADPUB_TYPE={
	    "INTEGER PRIMARY KEY AUTOINCREMENT", 
	    "text", 
	    "text"
    };
    
    private String AdPub     = null;
	public  void setAdPub(String adPub){
		boolean notify = false;
		if(AdPub == null ){
			if(adPub != null)notify = true;
		}else{
			if(adPub == null || !(AdPub.equals(adPub)))notify = true;
		}
		AdPub     = adPub;
		if(notify){
			notifyDBAdPubChanged();
		}
	}
	public String getAdPub(){
		return AdPub;
	}
	public  boolean CheckAdPubAviable(){
		if(AdPub == null || "".equals(AdPub))return false;
		return true;
	}
	public static boolean CheckAdPubAviable(String adPub){
		if(adPub == null || "".equals(adPub))return false;
		return true;
	}
	public void setState(String state){
		setState(AdPub,state);
	}
	public void setState(String AdPub , String state){
		if(CheckAdPubAviable() && DownloadUtils.CheckDownloadStateAviable(state)){
			if(IsAdPubDBExist(AdPub)){
				 
			}
		}
	}
	public boolean IsAdPubDBExist(String adPub){
		Cursor c = getAdPubDB(adPub);
		return c.moveToFirst();
	}
	private Cursor getAdPubDB(){	
	   return MySQLiteOpenHelper.getInstance().select(TABLE_AD, ADPUB_CHECK , null, null, null, null, null);
	}
	private Cursor getAdPubDB(String adPub){
	   	 if(CheckAdPubAviable(adPub))return null;
		 String[] selectionArgs ={adPub};
    	 return MySQLiteOpenHelper.getInstance().select(TABLE_AD, ADPUB_CHECK , XMLTAG.AD_TAG+"=?", selectionArgs, null, null, null);
	}
	private String getAdPubDBIdByAdPub(String adPub){
	   	 Cursor c = getAdPubDB(adPub);
	   	 if(c.moveToFirst()){
	   		 return c.getString(INDEX_DB_ID);
	   	 }
	   	 return null;
    }
	public boolean setAdPub(String[] adpub){
	   	 if(adpub==null || adpub.length<INDEX_ADPUB_MAX)return false;
	   	 boolean resoult=false;
	   	 String adPub = adpub[0];
	   	 if(CheckAdPubAviable(adPub))return false;
	   	 if(IsAdPubDBExist(adPub)){
	   		 if(MySQLiteOpenHelper.getInstance().update(TABLE_AD, ADPUB_INSERT, adpub, DB_ID+"=?", new String []{getAdPubDBIdByAdPub(adPub)+" "} )==1)resoult=true;
	   	 }else{
	   		 if(MySQLiteOpenHelper.getInstance().insert(TABLE_AD, ADPUB_INSERT, adpub)==1)resoult=true;
	   	 }
	   	 notifyDBAdPubChanged(adPub);
	   	 return resoult;
    } 
	
}
