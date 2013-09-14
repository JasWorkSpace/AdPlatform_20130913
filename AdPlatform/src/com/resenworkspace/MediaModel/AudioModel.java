package com.resenworkspace.MediaModel;

import android.content.Context;
import android.net.Uri;

import com.resenworkspace.adplatform.MediaHelper;
import com.resenworkspace.data.Utils.CharacterSets;
import com.resenworkspace.data.XML.AdException;
import com.resenworkspace.data.XML.RegionMediaModel;
import com.resenworkspace.data.XML.RegionModel;

public class AudioModel extends RegionMediaModel{

	private static String mName = "AudioModel";
	public AudioModel(Context context, String tag,Uri uri, RegionModel region)
            throws AdException {
        this(context, tag,null, uri, region);        
    }

    public AudioModel(Context context, String tag,String contentType,
            Uri uri, RegionModel region) throws AdException {
        super(context,tag, contentType , MediaHelper.MEDIA_TAG_VIDEO , mName, uri, region);
    }

}
