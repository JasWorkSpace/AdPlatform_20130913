package com.resenworkspace.adplatform;

import com.ResenWorkSpace.ResenWorkSpace.Log;
import com.resenworkspace.data.XML.AdException;
import com.resenworkspace.data.XML.ContentRestriction;
import com.resenworkspace.data.XML.ContentRestrictionException;
import com.resenworkspace.data.XML.ContentRestrictionFactory;
import com.resenworkspace.data.XML.ContentType;
import com.resenworkspace.data.XML.ElementTime;
import com.resenworkspace.data.XML.Event;
import com.resenworkspace.data.XML.ItemLoadedFuture;
import com.resenworkspace.data.XML.MediaModel;
import com.resenworkspace.data.XML.RegionMediaModel;
import com.resenworkspace.data.XML.RegionModel;
import com.resenworkspace.data.XML.MediaModel.MediaAction;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;

public class VideoModel extends RegionMediaModel{
    
	private String TAG = "VideoModel";
	private ItemLoadedFuture mItemLoadedFuture;
 
   
    public VideoModel(Context context, String tag,Uri uri, RegionModel region)
            throws AdException {
        this(context, tag,null, null, uri, region);
        initModelFromUri(uri);
        checkContentRestriction();
    }

    public VideoModel(Context context, String tag,String contentType, String src,
            Uri uri, RegionModel region) throws AdException {
        super(context,tag, MediaHelper.MEDIA_TAG_VIDEO, src, uri, region);
    }

    private void initModelFromUri(Uri uri) throws AdException {
        String scheme = uri.getScheme();
        if (uri.getScheme().equals("file")) {
            initFromFile(uri);
        }
        initMediaDuration();
    }

    private void initFromFile(Uri uri) {
        mSrc = uri.getPath();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        String extension = MimeTypeMap.getFileExtensionFromUrl(mSrc);
        if (TextUtils.isEmpty(extension)) {
            // getMimeTypeFromExtension() doesn't handle spaces in filenames nor can it handle
            // urlEncoded strings. Let's try one last time at finding the extension.
            int dotPos = mSrc.lastIndexOf('.');
            if (0 <= dotPos) {
                extension = mSrc.substring(dotPos + 1);
            }
        }
        mContentType = mimeTypeMap.getMimeTypeFromExtension(extension);
        // It's ok if mContentType is null. Eventually we'll show a toast telling the
        // user the video couldn't be attached.
        Log.i(TAG, "New VideoModel initFromFile created:"
                + " mSrc=" + mSrc
                + " mContentType=" + mContentType
                + " mUri=" + uri);
    }


    // EventListener Interface
    public void handleEvent(Event evt) {
        String evtType = evt.getType();
        Log.i(TAG, "[VideoModel] handleEvent " + evt.getType() + " on " + this);

        MediaAction action = MediaAction.NO_ACTIVE_ACTION;
        if (evtType.equals(MediaControl.START_EVENT)) {
            action = MediaAction.START;

            // if the Music player app is playing audio, we should pause that so it won't
            // interfere with us playing video here.
            pauseMusicPlayer();

            mVisible = true;
        } else if (evtType.equals(MediaControl.END_EVENT)) {
            action = MediaAction.STOP;
            if (mFill != ElementTime.FILL_FREEZE) {
                mVisible = false;
            }
        } else if (evtType.equals(MediaControl.PAUSE_EVENT)) {
            action = MediaAction.PAUSE;
            mVisible = true;
        } else if (evtType.equals(MediaControl.SEEK_EVENT)) {
            action = MediaAction.SEEK;
            //mSeekTo = ((EventImpl) evt).getSeekTo();
            mVisible = true;
        }

        appendAction(action);
        notifyModelChanged(false);
    }

    protected void checkContentRestriction() throws ContentRestrictionException {
        ContentRestriction cr = ContentRestrictionFactory.getContentRestriction();
        cr.checkVideoContentType(mContentType);
    }

    @Override
    protected boolean isPlayable() {
        return true;
    }

    //public ItemLoadedFuture loadThumbnailBitmap(ItemLoadedCallback callback) {
        //ThumbnailManager thumbnailManager = AdPlatformApplication.getApplication().getThumbnailManager();
       // mItemLoadedFuture = thumbnailManager.getVideoThumbnail(getUri(), callback);
        //return mItemLoadedFuture;
    //}

    public void cancelThumbnailLoading() {
        if (mItemLoadedFuture != null) {
            Log.i(TAG, "cancelThumbnailLoading for: " + this);
            mItemLoadedFuture.cancel();
            mItemLoadedFuture = null;
        }
    }
}
