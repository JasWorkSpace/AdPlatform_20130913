package com.resenworkspace.data.XML;

import java.util.ArrayList;





public class DB {
	protected ArrayList<IDBChangedObserver> mDBChangedObservers =
            new ArrayList<IDBChangedObserver>();
	
	public void registerDBChangedObserver(IDBChangedObserver observer) {
        if (!mDBChangedObservers.contains(observer)) {
        	mDBChangedObservers.add(observer);
        }
    }

    public void unregisterDBChangedObserver(IDBChangedObserver observer) {
    	mDBChangedObservers.remove(observer);
    }
    
    protected void notifyDBChanged(String ItemID) {
        for (IDBChangedObserver observer : mDBChangedObservers) {
            observer.onDBChanged(this, ItemID);
        }
    }
    
    protected void notifyDBAdPubChanged() {
        for (IDBChangedObserver observer : mDBChangedObservers) {
            observer.onAdPubChanged();
        }
    }
    
}
