package com.resenworkspace.data.DB;

import com.resenworkspace.adplatform.XMLTAG;
import com.resenworkspace.data.XML.DB;

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
    public static final String DOWNLOAD_FINISH  = "finish";
    public static final String DOWNLOAD_LOADING = "loading";
    public static final String DOWNLOAD_WAITING = "waiting";
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
	public boolean CheckAdPubAviable(){
		if(AdPub == null || "".equals(AdPub))return false;
		return true;
	}
}
