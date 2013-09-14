package com.resenworkspace.data.XML;

import android.content.ContentResolver;

public interface ContentRestriction {

	void CheckSupportFileType(String type) throws ContentRestrictionException;

	void checkMessageSize(int mCurrentMessageSize, int increaseSize,
			ContentResolver contentResolver);
	void checkImageContentType(String contentType) throws ContentRestrictionException;

    void checkAudioContentType(String contentType) throws ContentRestrictionException;

    void checkVideoContentType(String contentType) throws ContentRestrictionException;

    void checkResolution(int width, int height) throws ContentRestrictionException;
}
