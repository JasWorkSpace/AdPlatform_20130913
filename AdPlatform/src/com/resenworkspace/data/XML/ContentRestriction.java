package com.resenworkspace.data.XML;

import android.content.ContentResolver;

public interface ContentRestriction {

	void CheckSupportFileType(String type) throws ContentRestrictionException;

	void checkMessageSize(int mCurrentMessageSize, int increaseSize,
			ContentResolver contentResolver);
}
