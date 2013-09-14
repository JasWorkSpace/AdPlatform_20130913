package com.resenworkspace.layout;

import java.util.ArrayList;

import com.ResenWorkSpace.ResenWorkSpace.Log;
import com.resenworkspace.data.XML.IModelChangedObserver;
import com.resenworkspace.data.XML.Model;
import com.resenworkspace.data.XML.RegionModel;

public class LayoutModel extends Model{

	private static final String TAG = "LayoutModel";

    public static final String IMAGE_REGION_ID = "Image";
    public static final String TEXT_REGION_ID  = "Text";
    
    public static final int LAYOUT_BOTTOM_TEXT = 0;
    public static final int LAYOUT_TOP_TEXT    = 1;
    public static final int DEFAULT_LAYOUT_TYPE = LAYOUT_BOTTOM_TEXT;

    private int mLayoutType = DEFAULT_LAYOUT_TYPE;
    private RegionModel mRootLayout;
    private RegionModel mImageRegion;
    private RegionModel mTextRegion;
    private ArrayList<RegionModel> mNonStdRegions;
    private LayoutParameters mLayoutParams;

    public LayoutModel() {
        mLayoutParams = LayoutManager.getInstance().getLayoutParameters();
        // Create default root-layout and regions.
        createDefaultRootLayout();
        createDefaultImageRegion();
        createDefaultTextRegion();
    }

    public LayoutModel(RegionModel rootLayout, ArrayList<RegionModel> regions) {
        mLayoutParams = LayoutManager.getInstance().getLayoutParameters();
        mRootLayout = rootLayout;
        mNonStdRegions = new ArrayList<RegionModel>();

        for (RegionModel r : regions) {
            String rId = r.getRegionId();
            if (rId.equals(IMAGE_REGION_ID)) {
                mImageRegion = r;
            } else if (rId.equals(TEXT_REGION_ID)) {
                mTextRegion = r;
            } else {
                Log.i(TAG, "Found non-standard region: " + rId);
                mNonStdRegions.add(r);
            }
        }

        validateLayouts();
    }

    private void createDefaultRootLayout() {
        mRootLayout = new RegionModel(null, 0, 0, mLayoutParams.getWidth(),
                                                  mLayoutParams.getHeight());
    }

    private void createDefaultImageRegion() {
        if (mRootLayout == null) {
            throw new IllegalStateException("Root-Layout uninitialized.");
        }

        mImageRegion = new RegionModel(IMAGE_REGION_ID, 0, 0,
                mRootLayout.getWidth(), mLayoutParams.getImageHeight());
    }

    private void createDefaultTextRegion() {
        if (mRootLayout == null) {
            throw new IllegalStateException("Root-Layout uninitialized.");
        }

        mTextRegion = new RegionModel(
                TEXT_REGION_ID, 0, mLayoutParams.getImageHeight(),
                mRootLayout.getWidth(), mLayoutParams.getTextHeight());
    }

    private void validateLayouts() {
        if (mRootLayout == null) {
            createDefaultRootLayout();
        }

        if (mImageRegion == null) {
            createDefaultImageRegion();
        }

        if (mTextRegion == null) {
            createDefaultTextRegion();
        }
    }

    public RegionModel getRootLayout() {
        return mRootLayout;
    }

    public void setRootLayout(RegionModel rootLayout) {
        mRootLayout = rootLayout;
    }

    public RegionModel getImageRegion() {
        return mImageRegion;
    }

    public void setImageRegion(RegionModel imageRegion) {
        mImageRegion = imageRegion;
    }

    public RegionModel getTextRegion() {
        return mTextRegion;
    }

    public void setTextRegion(RegionModel textRegion) {
        mTextRegion = textRegion;
    }

    /**
     * Get all regions except root-layout. The result is READ-ONLY.
     */
    public ArrayList<RegionModel> getRegions() {
        ArrayList<RegionModel> regions = new ArrayList<RegionModel>();
        if (mImageRegion != null) {
            regions.add(mImageRegion);
        }
        if (mTextRegion != null) {
            regions.add(mTextRegion);
        }
        return regions;
    }

    public RegionModel findRegionById(String rId) {
        if (IMAGE_REGION_ID.equals(rId)) {
            return mImageRegion;
        } else if (TEXT_REGION_ID.equals(rId)) {
            return mTextRegion;
        } else {
            for (RegionModel r : mNonStdRegions) {
                if (r.getRegionId().equals(rId)) {
                    return r;
                }
            }

            Log.i(TAG, "Region not found: " + rId);
            return null;
        }
    }

    public int getLayoutWidth() {
        return mRootLayout.getWidth();
    }

    public int getLayoutHeight() {
        return mRootLayout.getHeight();
    }

    public String getBackgroundColor() {
        return mRootLayout.getBackgroundColor();
    }

    public void changeTo(int layout) {
        if (mRootLayout == null) {
            throw new IllegalStateException("Root-Layout uninitialized.");
        }

        if (mLayoutParams == null) {
            mLayoutParams = LayoutManager.getInstance().getLayoutParameters();
        }

        if (mLayoutType != layout) {
            switch (layout) {
                case LAYOUT_BOTTOM_TEXT: {
                    mImageRegion.setTop(0);
                    mTextRegion.setTop(mLayoutParams.getImageHeight());
                    mLayoutType = layout;
                    notifyModelChanged(true);
                }
                break;
                case LAYOUT_TOP_TEXT: {
                    mImageRegion.setTop(mLayoutParams.getTextHeight());
                    mTextRegion.setTop(0);
                    mLayoutType = layout;
                    notifyModelChanged(true);
                }
                break;
                default: {
                    Log.w(TAG, "Unknown layout type: " + layout);
                }
            }
        } else {
            Log.i(TAG, "Skip changing layout.");
        }
    }

    public int getLayoutType() {
        return mLayoutType;
    }

    @Override
    protected void registerModelChangedObserverInDescendants(
            IModelChangedObserver observer) {
        if (mRootLayout != null) {
            mRootLayout.registerModelChangedObserver(observer);
        }

        if (mImageRegion != null) {
            mImageRegion.registerModelChangedObserver(observer);
        }

        if (mTextRegion != null) {
            mTextRegion.registerModelChangedObserver(observer);
        }
    }

    @Override
    protected void unregisterModelChangedObserverInDescendants(
            IModelChangedObserver observer) {
        if (mRootLayout != null) {
            mRootLayout.unregisterModelChangedObserver(observer);
        }

        if (mImageRegion != null) {
            mImageRegion.unregisterModelChangedObserver(observer);
        }

        if (mTextRegion != null) {
            mTextRegion.unregisterModelChangedObserver(observer);
        }
    }

    @Override
    protected void unregisterAllModelChangedObserversInDescendants() {
        if (mRootLayout != null) {
            mRootLayout.unregisterAllModelChangedObservers();
        }

        if (mImageRegion != null) {
            mImageRegion.unregisterAllModelChangedObservers();
        }

        if (mTextRegion != null) {
            mTextRegion.unregisterAllModelChangedObservers();
        }
    }
}
