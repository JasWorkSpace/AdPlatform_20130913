package com.resenworkspace.data.DB;

import android.database.Cursor;

import com.resenworkspace.adplatform.XMLTAG;
import com.resenworkspace.data.XML.DB;
import com.resenworkspace.data.XML.MySQLiteOpenHelper;

public class DB_Scene extends DB{
	public static String TABLE_SCENE = XMLTAG.SCENE.SCENE_TAG;
	//Interface of DB tag
    public static String TABLE = TABLE_SCENE; 
    private static DB_Scene sInstance = null;
    synchronized static DB_Scene getInstance(){
	   	 if(sInstance == null){
	   		 sInstance = new DB_Scene();
	   	 }
	   	 return sInstance;
    }
    public static String DB_ID = "scene_id";
    public static int    INDEX_DB_ID         = 0;
    public static int    INDEX_ADPUB_ID      = 1;
    public static int    INDEX_SCENE_ID      = 2;
    public static int    INDEX_SCENE_NAME    = 3;
    public static int    INDEX_SCENE_BKIMGID = 4;
    public static int    INDEX_SCENE_TAG     = 5;
    public static int    INDEX_SCENE_X       = 6;
    public static int    INDEX_SCENE_Y       = 7;
    public static int    INDEX_SCENE_WIDTH   = 8;
    public static int    INDEX_SCENE_HEIGHT  = 9;
    private static int   INDEX_SCENE_MAX     = 9;
    
    public static String[] SCENE_INSERT = {
	   	 XMLTAG.AD_TAG,
	   	 XMLTAG.SCENE.SCENE_ID,
	   	 XMLTAG.SCENE.SCENE_NAME,
	   	 XMLTAG.SCENE.SCENE_BKIMGID,
	   	 XMLTAG.SCENE.RECT.RECT_TAG,//rect id use to control scene item,
	   	 XMLTAG.SCENE.RECT.RECT_X,
		 XMLTAG.SCENE.RECT.RECT_Y,
		 XMLTAG.SCENE.RECT.RECT_WIDTH,
		 XMLTAG.SCENE.RECT.RECT_HEIGHT
    };
    public static String[] SCENE_CHECK = {
   		 DB_ID,
   		 XMLTAG.AD_TAG,
	   	 XMLTAG.SCENE.SCENE_ID,
	   	 XMLTAG.SCENE.SCENE_NAME,
	   	 XMLTAG.SCENE.SCENE_BKIMGID,
	   	 XMLTAG.SCENE.RECT.RECT_TAG,
	   	 XMLTAG.SCENE.RECT.RECT_X,
		 XMLTAG.SCENE.RECT.RECT_Y,
		 XMLTAG.SCENE.RECT.RECT_WIDTH,
		 XMLTAG.SCENE.RECT.RECT_HEIGHT    		 
    };
    public static  String[] SCENE_TYPE={
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
    public Cursor getAdPubScene(String Adpub){
 	   if(!DB_AdPub.CheckAdPubAviable(Adpub))return null;
 	   String[] selectionArgs ={Adpub};
   	   return MySQLiteOpenHelper.getInstance().select(TABLE_SCENE, SCENE_CHECK , XMLTAG.AD_TAG+"=?", selectionArgs, null, null, null);
    }
    private boolean IsAdPubSceneExist(String Adpub){
 	   if(!DB_AdPub.CheckAdPubAviable(Adpub))return false;
 	   Cursor c = getAdPubScene(Adpub);
 	   return c.moveToFirst();
    }
    private String getAdPubSceneIdByAdPub(String adPub){
 	   	 Cursor c = getAdPubScene(adPub);
 	   	 if(c.moveToFirst()){
 	   		 return c.getString(INDEX_DB_ID);
 	   	 }
 	   	 return null;
    } 
    public boolean setScene(String[] scene){
 	   	 if(scene==null || scene.length != INDEX_SCENE_MAX)return false;
 	   	 boolean resoult=false;
 	   	 String adPub = scene[0];
 	   	 if(DB_AdPub.CheckAdPubAviable(adPub))return false;
 	   	 if(IsAdPubSceneExist(adPub)){
 	   		 if(MySQLiteOpenHelper.getInstance().update(TABLE_SCENE, SCENE_INSERT, scene, DB_ID+"=?", new String []{getAdPubSceneIdByAdPub(adPub)+" "} )==1)resoult=true;
 	   	 }else{
 	   		 if(MySQLiteOpenHelper.getInstance().insert(TABLE_SCENE, SCENE_INSERT, scene)==1)resoult=true;
 	   	 }
 	   	 return resoult;
   } 
   
   public boolean DelOption(String adPub){
 		boolean resoult=true;
 		if(IsAdPubSceneExist(adPub)){		
 			if(MySQLiteOpenHelper.getInstance().delete(TABLE_SCENE, DB_ID+"=?", new String []{getAdPubSceneIdByAdPub(adPub)+" "} )==1)resoult=true;
 		}
 		return resoult;
   }
}
