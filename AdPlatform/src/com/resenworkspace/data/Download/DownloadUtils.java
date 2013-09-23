package com.resenworkspace.data.Download;

public class DownloadUtils {

	public static final String DOWNLOAD_FINISH  = "finish";
    public static final String DOWNLOAD_LOADING = "loading";
    public static final String DOWNLOAD_WAITING = "waiting";
    
    public static final String DOWNLOAD_STATE   = "state";
    
    public static boolean CheckDownloadStateAviable(String state){
    	if(state == null)return false;
    	if(DOWNLOAD_FINISH.equals(state)
    			|| DOWNLOAD_LOADING.equals(state)
    			|| DOWNLOAD_WAITING.equals(state))
    		return true;
    	return false;
    }
}
