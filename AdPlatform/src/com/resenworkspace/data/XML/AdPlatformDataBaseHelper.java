package com.resenworkspace.data.XML;

import com.resenworkspace.data.DB.DB_AdPub;
import com.resenworkspace.data.DB.DB_File;
import com.resenworkspace.data.DB.DB_Option;

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
    	DB_File.TABLE_FILE,
    	DB_Option.TABLE_OPTION
    };
    public  static String[][] FieldNames = {
    	DB_AdPub.ADPUB_CHECK,
    	DB_File.FILE_CHECK,
    	DB_Option.OPTION_CHECK
    };
    public  static String[][] FieldType = {
    	DB_AdPub.ADPUB_TYPE,
    	DB_File.FIELD_TYPE,
    	DB_Option.OPTION_TYPE
    };
}
