package com.resenworkspace.data.XML;



public interface IDBChangedObserver {
    
	void onDBChanged(DB db , String ItemID);
	
	void onAdPubChanged();
	
	void onAdPubChanged(String AdPub);
	
	void onDownLoadChanged(String AdPub ,String FileId);
}
