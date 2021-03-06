package com.resenworkspace.layout;

import com.ResenWorkSpace.ResenWorkSpace.Log;

import android.content.Context;
import android.content.res.Configuration;

public class LayoutManager {

	private static final String TAG = "LayoutManager";
    
    private final Context mContext;
    private LayoutParameters mLayoutParams;

    private static LayoutManager sInstance;

    private LayoutManager(Context context) {
        mContext = context;
        initLayoutParameters(context.getResources().getConfiguration());
    }

    private void initLayoutParameters(Configuration configuration) {
        mLayoutParams = getLayoutParameters(
                configuration.orientation == Configuration.ORIENTATION_PORTRAIT
                ? LayoutParameters.HVGA_PORTRAIT
                : LayoutParameters.HVGA_LANDSCAPE);
        Log.i(TAG, "LayoutParameters: " + mLayoutParams.getTypeDescription()
                + ": " + mLayoutParams.getWidth() + "x" + mLayoutParams.getHeight());
    }

    private LayoutParameters getLayoutParameters(int displayType) {
        switch (displayType) {
            case LayoutParameters.HVGA_LANDSCAPE:
                return new HVGALayoutParameters(mContext, LayoutParameters.HVGA_LANDSCAPE);
            case LayoutParameters.HVGA_PORTRAIT:
                return new HVGALayoutParameters(mContext, LayoutParameters.HVGA_PORTRAIT);
        }

        throw new IllegalArgumentException(
                "Unsupported display type: " + displayType);
    }

    public static void init(Context context) {
        Log.i(TAG, "DefaultLayoutManager.init()");

        if (sInstance != null) {
            Log.w(TAG, "Already initialized.");
        }
        sInstance = new LayoutManager(context);
    }

    public static LayoutManager getInstance() {
        if (sInstance == null) {
            throw new IllegalStateException("Uninitialized.");
        }
        return sInstance;
    }

    public void onConfigurationChanged(Configuration newConfig) {
        Log.i(TAG, "-> LayoutManager.onConfigurationChanged().");
        initLayoutParameters(newConfig);
    }

    public int getLayoutType() {
        return mLayoutParams.getType();
    }

    public int getLayoutWidth() {
        return mLayoutParams.getWidth();
    }

    public int getLayoutHeight() {
        return mLayoutParams.getHeight();
    }

    public LayoutParameters getLayoutParameters() {
        return mLayoutParams;
    }

}
