package com.resenworkspace.adplatform;

import java.io.UnsupportedEncodingException;

import com.ResenWorkSpace.ResenWorkSpace.Log;
import com.resenworkspace.data.Utils.CharacterSets;
import com.resenworkspace.data.XML.RegionMediaModel;
import com.resenworkspace.data.XML.RegionModel;

import android.content.Context;

public class TEXTModel extends RegionMediaModel{

	private static final String TAG = "TEXTModel";
    private CharSequence mText;
    private final int mCharset;
    private final static String mName = "text";
    public TEXTModel(Context context,String tag, RegionModel region) {
        this(context,tag, new byte[0], region);
    }
    
    public TEXTModel(Context context,String tag, byte[] data, RegionModel region) {
        super(context, tag ,MediaHelper.MEDIA_TAG_TEXT, mName ,data != null ? data : new byte[0] ,region);
        mCharset = CharacterSets.UTF_8;
        mText = extractTextFromData(data);
    }

    private CharSequence extractTextFromData(byte[] data) {
        if (data != null) {
            try {
                if (CharacterSets.ANY_CHARSET == mCharset) {
                    return new String(data); // system default encoding.
                } else {
                    String name = CharacterSets.getMimeName(mCharset);
                    return new String(data, name);
                }
            } catch (UnsupportedEncodingException e) {
                Log.e(TAG, "Unsupported encoding: " + mCharset, e);
                return new String(data); // system default encoding.
            }
        }
        return "";
    }

    public String getText() {
        if (mText == null) {
            return null;
        }   
        // If our internal CharSequence is not already a String,
        // re-save it as a String so subsequent calls to getText will
        // be less expensive.
        if (!(mText instanceof String)) {
            mText = mText.toString();
        }

        return mText.toString();
    }

    public void setText(CharSequence text) {
        mText = text;
        notifyModelChanged(true);
    }

    public void cloneText() {
        mText = new String(mText.toString());
    }

    public int getCharset() {
        return mCharset;
    }

    // EventListener Interface
//    public void handleEvent(Event evt) {
//        if (evt.getType().equals(SmilMediaElementImpl.SMIL_MEDIA_START_EVENT)) {
//            mVisible = true;
//        } else if (mFill != ElementTime.FILL_FREEZE) {
//            mVisible = false;
//        }
//
//        notifyModelChanged(false);
//    }

}
