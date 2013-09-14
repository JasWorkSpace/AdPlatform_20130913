package com.resenworkspace.adplatform;

import com.resenworkspace.layout.LayoutManager;

import android.app.Application;
import android.content.res.Configuration;

public class AdPlatformApplication extends Application{

	private static AdPlatformApplication AdApp;
	synchronized public static AdPlatformApplication getApplication() {
        return AdApp;
    }
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		AdApp = this;
		Initresource();
	}
	
	private void Initresource() {
		// TODO Auto-generated method stub
		AdPlatformConfig.init(this);
		LayoutManager.init(this);
	}
	
	@Override
    public void onConfigurationChanged(Configuration newConfig) {
        LayoutManager.getInstance().onConfigurationChanged(newConfig);
    }

	

}
