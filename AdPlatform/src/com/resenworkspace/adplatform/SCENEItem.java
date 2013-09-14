package com.resenworkspace.adplatform;

import com.resenworkspace.data.XML.AdException;
import com.resenworkspace.data.XML.RegionMediaModel;
import com.resenworkspace.data.XML.RegionModel;

import android.content.Context;
import android.net.Uri;

public class SCENEItem extends RegionMediaModel{

	 public SCENEItem(Context context, String tag, String contentType,
			String src, byte[] data, RegionModel region) {
		super(context, tag, contentType, src, data, region);
		// TODO Auto-generated constructor stub
	}

	public SCENEItem(Context context, String tag, Uri uri, RegionModel region)
			throws AdException {
		super(context, tag, uri, region);
		// TODO Auto-generated constructor stub
    }
    

	public SCENEItem(Context context, String tag, String contentType,
			String src, Uri uri, RegionModel region) throws AdException {
		super(context, tag, contentType, src, uri, region);
		// TODO Auto-generated constructor stub
	}

	private int mSlideSize;
	
	 public int getSlideSize() {
	        return mSlideSize;
	 }
	 public void increaseSlideSize(int increaseSize) {
	     if (increaseSize > 0) {
	         mSlideSize += increaseSize;
	     }
	 }

	 public void decreaseSlideSize(int decreaseSize) {
	     if (decreaseSize > 0) {
	         mSlideSize -= decreaseSize;
	     }
	 }
}
