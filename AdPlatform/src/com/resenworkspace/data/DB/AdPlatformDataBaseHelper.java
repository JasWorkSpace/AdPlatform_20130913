package com.resenworkspace.data.DB;

import com.resenworkspace.data.XML.DB;
import com.resenworkspace.data.XML.MySQLiteOpenHelper;

import android.content.Context;

public class AdPlatformDataBaseHelper extends DB{
     
	/* version must greater than 1 */
	public  static final int    version = 1;
	public  static final String DBname="AdDataBase";
	
    public  static void Init(Context context){
    	new MySQLiteOpenHelper(context , DBname , null , version,
    			TABLES , FieldNames , FieldType);
    }
	
    public  static String[] TABLES = {
    	DB_AdPub.TABLE_AD,
    	DB_File.TABLE_FILE
    };
    public  static String[][] FieldNames = {
    	DB_AdPub.ADPUB_CHECK,
    	DB_File.FILE_CHECK
    };
    public  static String[][] FieldType = {
    	DB_AdPub.ADPUB_TYPE,
    	DB_File.FIELD_TYPE
    };
}
