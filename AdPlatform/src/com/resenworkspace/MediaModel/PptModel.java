package com.resenworkspace.MediaModel;

import android.content.Context;
import android.net.Uri;

import com.resenworkspace.adplatform.MediaHelper;
import com.resenworkspace.data.XML.AdException;
import com.resenworkspace.data.XML.RegionMediaModel;
import com.resenworkspace.data.XML.RegionModel;

public class PptModel extends RegionMediaModel{

	private static String mName = "PptModel";
	public PptModel(Context context, String tag,Uri uri, RegionModel region)
            throws AdException {
        this(context, tag,null, null, uri, region);
        
    }

    public PptModel(Context context, String tag,String contentType, String MediaType,
            Uri uri, RegionModel region) throws AdException {
    	super(context,tag, contentType , MediaHelper.MEDIA_TAG_PPT , mName, uri, region);
    }

	
}
