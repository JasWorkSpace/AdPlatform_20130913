package com.resenworkspace.data.XML;

import java.util.ArrayList;

import com.resenworkspace.adplatform.AdPlatformConfig;

import android.content.ContentResolver;

public class CarrierContentRestriction implements ContentRestriction{
   
	private static final ArrayList<String> sSupportedImageTypes;
    private static final ArrayList<String> sSupportedAudioTypes;
    private static final ArrayList<String> sSupportedVideoTypes;

    static {
        sSupportedImageTypes = ContentType.getImageTypes();
        sSupportedAudioTypes = ContentType.getAudioTypes();
        sSupportedVideoTypes = ContentType.getVideoTypes();
    }

	@Override
	public void CheckSupportFileType(String type)
			throws ContentRestrictionException {
		// TODO Auto-generated method stub
		
	}
	
    public void checkImageContentType(String contentType)
            throws ContentRestrictionException {
         if (null == contentType) {
            throw new ContentRestrictionException("Null content type to be check");
         }
         if (!sSupportedImageTypes.contains(contentType)) {
            throw new UnsupportContentTypeException("Unsupported image content type : "
                    + contentType);
         }
     }

     public void checkAudioContentType(String contentType)
            throws ContentRestrictionException {
         if (null == contentType) {
            throw new ContentRestrictionException("Null content type to be check");
         }
         if (!sSupportedAudioTypes.contains(contentType)) {
            throw new UnsupportContentTypeException("Unsupported audio content type : "
                    + contentType);
         }
     }

     public void checkVideoContentType(String contentType)
            throws ContentRestrictionException {
         if (null == contentType) {
            throw new ContentRestrictionException("Null content type to be check");
         }
         if (!sSupportedVideoTypes.contains(contentType)) {
            throw new UnsupportContentTypeException("Unsupported video content type : "
                    + contentType);
         }
     }

	@Override
	public void checkMessageSize(int mCurrentSize, int increaseSize,
			ContentResolver contentResolver) {
		// TODO Auto-generated method stub
		if ( (mCurrentSize < 0) || (increaseSize < 0) ) {
            throw new ContentRestrictionException("Negative message size"
                    + " or increase size");
        }
        int newSize = mCurrentSize + increaseSize;

        if ( (newSize < 0) || (newSize > AdPlatformConfig.getMaxAdSize()) ) {
            throw new ExceedAdSizeException("Exceed message size limitation");
        }
	}

	@Override
	public void checkResolution(int width, int height)
			throws ContentRestrictionException {
		// TODO Auto-generated method stub
		if ( (width > AdPlatformConfig.getMaxWidth()) || (height > AdPlatformConfig.getMaxHeight()) ) {
            throw new ResolutionException("content resolution exceeds restriction.");
        }
	}
}
