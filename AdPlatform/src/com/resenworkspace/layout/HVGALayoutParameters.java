package com.resenworkspace.layout;

import com.ResenWorkSpace.ResenWorkSpace.Log;

import android.annotation.SuppressLint;
import android.content.Context;


public class HVGALayoutParameters implements LayoutParameters {
	
    private static final String TAG = "HVGALayoutParameters";

    private int mType = -1;

    private static int mImageHeightLandscape;
    private static int mTextHeightLandscape;
    private static int mImageHeightPortrait;
    private static int mTextHeightPortrait;
    private static int mMaxHeight;
    private static int mMaxWidth;

    @SuppressLint("NewApi")
	public HVGALayoutParameters(Context context, int type) {
        if ((type != HVGA_LANDSCAPE) && (type != HVGA_PORTRAIT)) {
            throw new IllegalArgumentException(
                    "Bad layout type detected: " + type);
        }
        Log.i(TAG, "HVGALayoutParameters.<init>(" + type + ").");
        mType = type;

        float scale = context.getResources().getDisplayMetrics().density;
        mMaxWidth = (int) (context.getResources().getConfiguration().screenWidthDp * scale + 0.5f);
        mMaxHeight =
            (int) (context.getResources().getConfiguration().screenHeightDp * scale + 0.5f);

        mImageHeightLandscape = (int) (mMaxHeight * .90f);
        mTextHeightLandscape = (int) (mMaxHeight * .10f);
        mImageHeightPortrait = (int) (mMaxWidth * .90f);
        mTextHeightPortrait = (int) (mMaxWidth * .10f);

       
        Log.i(TAG, "HVGALayoutParameters mMaxWidth: " + mMaxWidth +
                " mMaxHeight: " + mMaxHeight +
                " mImageHeightLandscape: " + mImageHeightLandscape +
                " mTextHeightLandscape: " + mTextHeightLandscape +
                " mImageHeightPortrait: " + mImageHeightPortrait +
                " mTextHeightPortrait: " + mTextHeightPortrait);
    }

    public int getWidth() {
        return mType == HVGA_LANDSCAPE ? mMaxWidth
                                       : mMaxHeight;
    }

    public int getHeight() {
        return mType == HVGA_LANDSCAPE ? mMaxHeight
                                       : mMaxWidth;
    }

    public int getImageHeight() {
        return mType == HVGA_LANDSCAPE ? mImageHeightLandscape
                                       : mImageHeightPortrait;
    }

    public int getTextHeight() {
        return mType == HVGA_LANDSCAPE ? mTextHeightLandscape
                                       : mTextHeightPortrait;
    }

    public int getType() {
        return mType;
    }

    public String getTypeDescription() {
        return mType == HVGA_LANDSCAPE ? "HVGA-L" : "HVGA-P";
    }
}

