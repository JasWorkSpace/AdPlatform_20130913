package com.resenworkspace.data.Download;

import android.database.Cursor;

import com.resenworkspace.data.DB.DB_Download;
import com.resenworkspace.data.XML.DB;
import com.resenworkspace.data.XML.IDBChangedObserver;

public class DownloadManager implements IDBChangedObserver{
    
	private String mDownloadingAdPub = null;
	
	public DownloadManager(){
		DB_Download.getInstance().registerDBChangedObserver(this);
	}

	@Override
	public void onDBChanged(DB db, String ItemID) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onAdPubChanged() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onAdPubChanged(String AdPub) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onDownLoadChanged(String AdPub, String FileId) {
		// TODO Auto-generated method stub
		if(mDownloadingAdPub != null){
			Cursor c = DB_Download.getInstance().getDownload(AdPub);
			
		}
	}
}
