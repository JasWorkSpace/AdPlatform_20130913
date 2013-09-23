package com.resenworkspace.data.Download;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class AdConfig {

	  private String USER            = "test21";
	  private String FTP_HOST        = "www.joysw.com";
	  private int    FTP_PORT        = 21;
	  private String FTP_USER        = "test21";
	  private String FTP_POSSWORD    = "test21";
	  private String FTP_CONNECTMODE = "pasv";
	  private String FTP_USERDIR     = "/adpublish";
	  
	  private boolean    LoadOK = false;
	  private Properties props;
	  private static AdConfig mAdKeyConfig;
	  public  static AdConfig getInstance(){
		if(mAdKeyConfig == null){
			mAdKeyConfig = new AdConfig();
		}
		return mAdKeyConfig;
	  }
	  public AdConfig(){
		  Load();
		  InitResource();
	  }
	  
	  private void Load(){   
        try {
        	InputStream is = this.getClass().getResourceAsStream("/com/resenworkspace/data/Download/adconfig.properties");
            props = new Properties();
			props.load(is);
			LoadOK = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	  private void InitResource(){
		  USER            = getString("USER",USER);
		  FTP_HOST        = getString("FTP_HOST",FTP_HOST);
		  FTP_PORT        = getInt("FTP_PORT",FTP_PORT);
		  FTP_USER        = getString("FTP_USER",FTP_USER);
		  FTP_POSSWORD    = getString("FTP_POSSWORD",FTP_POSSWORD);
		  FTP_CONNECTMODE = getString("FTP_CONNECTMODE",FTP_CONNECTMODE);
		  FTP_USERDIR     = getString("FTP_USERDIR",FTP_USERDIR);
	  }
	 public String getUSER(){
		 return USER;
	 }  
	 public String getFTPHost(){
		 return FTP_HOST;
	 }
	 public int getFTPPort(){
		 return FTP_PORT;
	 } 
	 public String getFTPUser(){
		 return FTP_USER;
	 }
	 public String getFTPPossword(){
		 return FTP_POSSWORD;
	 }
	 public String getFTPConnectMode(){
		 return FTP_CONNECTMODE;
	 }
	 public String getFTPUserDir(){
		 return FTP_USERDIR;
	 }
	   //base Interface for prop
		private String  getString(String KEY,String Default){
			String workspace = getString(KEY);
			if(workspace == null || "".equals(workspace))return Default;
			return workspace;
		}
		private boolean getBoolean(String KEY,boolean Default){
			String workspace = getString(KEY);
			if(workspace==null || "".equals(workspace))return Default;
			return Boolean.parseBoolean(workspace);
		}
		private int getInt(String KEY,int Default){
			String workspace = getString(KEY);
			if(workspace==null || "".equals(workspace))return Default;
			return Integer.parseInt(workspace);
		}
		private String getString(String KEY){
			if(LoadOK){
				return props.getProperty(KEY).trim();
			}
			return null;
		}
}
