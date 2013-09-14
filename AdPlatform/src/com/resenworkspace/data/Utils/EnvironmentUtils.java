package com.resenworkspace.data.Utils;

import java.io.File;

import android.os.Environment;

public class EnvironmentUtils {

	   public static boolean SDExist(){
	        boolean ret = false;        
	        if (Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
	            ret = true;
	        }        
	        return ret;
	    }
	    
	    public static String getSDPath(){ 
	        File sd = null;
	        sd = Environment.getExternalStorageDirectory();
	        return sd.toString(); 
	    }
        
	    public static String getSizeString(long size) {
	        if (size < 1024) {
	            return String.valueOf(size) + "B";
	        }
	        else {
	            size = size / 1024;
	        }
	        if (size < 1024) {
	            return String.valueOf(size) + "KB";
	        }
	        else {
	            size = size * 100 / 1024;
	        }

	        return String.valueOf((size / 100)) + "." + ((size % 100) < 10 ? "0" : "")
	            + String.valueOf((size % 100)) + "MB";
	    }
}
