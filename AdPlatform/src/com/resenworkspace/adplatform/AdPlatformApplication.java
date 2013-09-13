package com.resenworkspace.adplatform;

import android.app.Application;

public class AdPlatformApplication extends Application{

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Initresource();
	}

	private void Initresource() {
		// TODO Auto-generated method stub
		AdPlatformConfig.init(this);
	}

	

}
