package com.resenworkspace.data.DB;

import android.database.Cursor;

import com.resenworkspace.adplatform.XMLTAG;
import com.resenworkspace.data.XML.DB;
import com.resenworkspace.data.XML.MySQLiteOpenHelper;

public class DB_SceneItem extends DB{

	public static String TABLE_SCENE_Item = XMLTAG.SCENE.RECT.ITEM.ITEM_TAG;
	//Interface of DB tag
    public static String TABLE = TABLE_SCENE_Item; 
    private static DB_SceneItem sInstance = null;
    synchronized static DB_SceneItem getInstance(){
	   	 if(sInstance == null){
	   		 sInstance = new DB_SceneItem();
	   	 }
	   	 return sInstance;
    }
    public static String DB_ID = "sceneitem_id";
    public static int    INDEX_DB_ID             = 0;
    public static int    INDEX_ADPUB_ID          = 1;
    public static int    INDEX_RECT_ID           = 2;
    public static int    INDEX_SCENEItem_ID      = 3;
    public static int    INDEX_SCENEItem_NAME    = 4;
    public static int    INDEX_SCENEItem_TYPE    = 5;
    public static int    INDEX_SCENEItem_START   = 6;
    public static int    INDEX_SCENEItem_END     = 7;
    public static int    INDEX_SCENEItem_X       = 8;
    public static int    INDEX_SCENEItem_Y       = 9;
    public static int    INDEX_SCENEItem_WIDTH   = 10;
    public static int    INDEX_SCENEItem_HEIGHT  = 11;
    private static int   INDEX_SCENE_MAX         = 11;
    
    public static String[] SCENE_INSERT = {
	   	 XMLTAG.AD_TAG,
	   	 XMLTAG.SCENE.RECT.RECT_TAG,
	   	 XMLTAG.SCENE.RECT.ITEM.ITEM_ID,
	   	 XMLTAG.SCENE.RECT.ITEM.ITEM_NAME,
	   	 XMLTAG.SCENE.RECT.ITEM.ITEM_TYPE,
	   	 XMLTAG.SCENE.RECT.ITEM.ITEM_START,
	     XMLTAG.SCENE.RECT.ITEM.ITEM_END,
	     XMLTAG.SCENE.RECT.ITEM.ITEM_X,
	   	 XMLTAG.SCENE.RECT.ITEM.ITEM_Y,
	   	 XMLTAG.SCENE.RECT.ITEM.ITEM_WIDTH,
	   	 XMLTAG.SCENE.RECT.ITEM.ITEM_HEIGHT	   	 
    };
    public static String[] SCENE_CHECK = {
   		 DB_ID,
   		 XMLTAG.AD_TAG,
	   	 XMLTAG.SCENE.RECT.RECT_TAG,
	   	 XMLTAG.SCENE.RECT.ITEM.ITEM_ID,
	   	 XMLTAG.SCENE.RECT.ITEM.ITEM_NAME,
	   	 XMLTAG.SCENE.RECT.ITEM.ITEM_TYPE,
	   	 XMLTAG.SCENE.RECT.ITEM.ITEM_START,
	     XMLTAG.SCENE.RECT.ITEM.ITEM_END,
	     XMLTAG.SCENE.RECT.ITEM.ITEM_X,
	   	 XMLTAG.SCENE.RECT.ITEM.ITEM_Y,
	   	 XMLTAG.SCENE.RECT.ITEM.ITEM_WIDTH,
	   	 XMLTAG.SCENE.RECT.ITEM.ITEM_HEIGHT    		 
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
    public boolean setSceneItem(String[] item){
	   	 if(item==null || item.length!=INDEX_SCENE_MAX)return false;
	   	 boolean resoult=false;
	   	 String AdPub  = item[0];
	   	 if(!DB_AdPub.CheckAdPubAviable(AdPub))return false;
	   	 String RECTID = item[1];
	   	 if(CheckAdPubFileExist(AdPub,RECTID)){
	   		 if(MySQLiteOpenHelper.getInstance().update(TABLE_SCENE_Item, SCENE_INSERT, item, DB_ID+"=?", new String []{getFileDBIdByIDAndAdPub(AdPub,RECTID)+" "} )==1)resoult=true;
	   	 }else{
	   		 if(MySQLiteOpenHelper.getInstance().insert(TABLE_SCENE_Item, SCENE_INSERT, item)==1)resoult=true;
	   	 }
	   	 return resoult;
    }
    public Cursor getSceneItem(String AdPub,String rectId){
	   	 if(DB_AdPub.CheckAdPubAviable(AdPub))return null;
	   	 String[] selectionArgs ={AdPub,rectId};
	   	 return MySQLiteOpenHelper.getInstance().select(TABLE_SCENE_Item, SCENE_CHECK , XMLTAG.AD_TAG+"=?"+XMLTAG.SCENE.RECT.RECT_TAG+"=?", selectionArgs, null, null, null);
    }
    private Cursor getSceneItem(String AdPub){
    	 if(DB_AdPub.CheckAdPubAviable(AdPub))return null;
	   	 String[] selectionArgs ={AdPub};
	   	 return MySQLiteOpenHelper.getInstance().select(TABLE_SCENE_Item, SCENE_CHECK , XMLTAG.AD_TAG+"=?", selectionArgs, null, null, null);
    }
    private String getFileDBIdByIDAndAdPub(String AdPub,String rectId){
	   	 Cursor c = getSceneItem(AdPub,rectId);
	   	 if(c.moveToFirst()){
	   		 return c.getString(INDEX_DB_ID);
	   	 }
	   	 return null;
    } 
    private boolean CheckAdPubFileExist(String AdPub , String FileId){
	   	 Cursor c = getSceneItem(AdPub,FileId);
	   	 return c.moveToFirst();
    }
    
    public boolean DelScenItem(String AdPub){
    	Cursor c = getSceneItem(AdPub);
    	boolean result = true;
    	while(c.moveToFirst()){
    		DelScenItem(AdPub,c.getColumnName(INDEX_RECT_ID));
    	}
    	return result;
    }
    private boolean DelScenItem(String AdPub,String rectId){
    	boolean result = true;
    	if(CheckAdPubFileExist(AdPub,rectId)){
    		if(MySQLiteOpenHelper.getInstance().delete(TABLE_SCENE_Item, DB_ID+"=?", new String []{getFileDBIdByIDAndAdPub(AdPub,rectId)+" "} )==1)result=true;
    	}
    	return result;
    }
}
