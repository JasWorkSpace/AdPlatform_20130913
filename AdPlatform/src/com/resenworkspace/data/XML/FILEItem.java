package com.resenworkspace.data.XML;

import android.content.Context;
import android.database.Cursor;

import com.resenworkspace.data.XML.FILE.FILETYPE;


public class FILEItem {
     
	private int      mID    ;	
	private String   mName  ;
	private int      mDelay ;
	private FILETYPE mType  ;
	private String   mPath  ;
    private String   mMsg   ;
    
    public FILEItem(Context context,Cursor cursor){
    	
    }
	
}
