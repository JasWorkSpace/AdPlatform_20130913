package com.resenworkspace.data.XML;

public class RegionModel extends Model{

	private static final String DEFAULT_FIT = "meet";
    private final String mRegionId;
    private String mFit;
    private int mLeft;
    private int mTop;
    private int mWidth;
    private int mHeight;
    private String mBackgroundColor;

    public RegionModel(String regionId, int left, int top,
            int width, int height) {
        this(regionId, DEFAULT_FIT, left, top, width, height);
    }

    public RegionModel(String regionId, String fit, int left, int top,
            int width, int height) {
        this(regionId, fit, left, top, width, height, null);
    }

    public RegionModel(String regionId, String fit, int left, int top,
            int width, int height, String bgColor) {
        mRegionId = regionId;
        mFit = fit;
        mLeft = left;
        mTop = top;
        mWidth = width;
        mHeight = height;
        mBackgroundColor = bgColor;
    }

    /**
     * @return the mRegionId
     */
    public String getRegionId() {
        return mRegionId;
    }

    /**
     * @return the mFit
     */
    public String getFit() {
        return mFit;
    }

    /**
     * @param fit the mFit to set
     */
    public void setFit(String fit) {
        mFit = fit;
        notifyModelChanged(true);
    }

    /**
     * @return the mLeft
     */
    public int getLeft() {
        return mLeft;
    }

    /**
     * @param left the mLeft to set
     */
    public void setLeft(int left) {
        mLeft = left;
        notifyModelChanged(true);
    }

    /**
     * @return the mTop
     */
    public int getTop() {
        return mTop;
    }

    /**
     * @param top the mTop to set
     */
    public void setTop(int top) {
        mTop = top;
        notifyModelChanged(true);
    }

    /**
     * @return the mWidth
     */
    public int getWidth() {
        return mWidth;
    }

    /**
     * @param width the mWidth to set
     */
    public void setWidth(int width) {
        mWidth = width;
        notifyModelChanged(true);
    }

    /**
     * @return the mHeight
     */
    public int getHeight() {
        return mHeight;
    }

    /**
     * @param height the mHeight to set
     */
    public void setHeight(int height) {
        mHeight = height;
        notifyModelChanged(true);
    }

    /**
     * @return the mBackgroundColor
     */
    public String getBackgroundColor() {
        return mBackgroundColor;
    }

    /**
     * @param bgColor the mBackgroundColor to set
     */
    public void setBackgroundColor(String bgColor) {
        mBackgroundColor = bgColor;
        notifyModelChanged(true);
    }

}
