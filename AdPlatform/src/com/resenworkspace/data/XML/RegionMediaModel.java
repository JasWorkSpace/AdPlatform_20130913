package com.resenworkspace.data.XML;

import android.content.Context;
import android.net.Uri;


public abstract class RegionMediaModel extends MediaModel{
	protected RegionModel mRegion;
    protected boolean mVisible = true;

    public RegionMediaModel(Context context, String tag, Uri uri,
            RegionModel region) throws AdException {
        this(context, tag, null, null, uri, region);
    }

    public RegionMediaModel(Context context, String tag, String contentType,
            String src, Uri uri, RegionModel region) throws AdException {
        super(context, tag, contentType, src, uri);
        mRegion = region;
    }

    public RegionMediaModel(Context context, String tag, String contentType,
            String src, byte[] data, RegionModel region) {
        super(context, tag, contentType, src, data);
        mRegion = region;
    }

    public RegionModel getRegion() {
        return mRegion;
    }

    public void setRegion(RegionModel region) {
        mRegion = region;
        notifyModelChanged(true);
    }

    /**
     * @return the mVisible
     */
    public boolean isVisible() {
        return mVisible;
    }

    /**
     * @param visible the mVisible to set
     */
    public void setVisible(boolean visible) {
        mVisible = visible;
    }
	

}
