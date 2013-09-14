package com.resenworkspace.data.XML;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import com.ResenWorkSpace.ResenWorkSpace.Log;
import com.resenworkspace.adplatform.MediaHelper;

public class MediaModel extends Model{
  
	private String TAG ="MediaModel";
	private final static String MUSIC_SERVICE_ACTION = "com.android.music.musicservicecommand";

    protected Context mContext;   
    protected int     mBegin;      //media start time
    protected int     mDuration;   //media end time
    protected String  mTag;        //media tag :id
    protected String  mMediaType;  //media type
    protected String  mSrc;        // diaplay name 
    protected String  mContentType;// file type
    private   Uri     mUri;        //media uri
    private   byte[] mData;        //media data
    protected short mFill;         //
    protected int mSize;           //media size
    protected int mSeekTo;
    protected boolean mMediaResizeable;

    private final ArrayList<MediaAction> mMediaActions;
    public static enum MediaAction {
        NO_ACTIVE_ACTION,
        START,
        STOP,
        PAUSE,
        SEEK,
    }

    public MediaModel(Context context, String tag, String contentType,
            String src, Uri uri ,String mediatype) throws AdException {
        mContext = context;
        mTag = tag;
        mContentType = contentType;
        mSrc = src;
        mUri = uri;
        mMediaType   = mediatype;
        initMediaSize();
        mMediaActions = new ArrayList<MediaAction>();
    }

    public MediaModel(Context context, String tag, String contentType,
            String src, byte[] data ,String mediatype) {
        if (data == null) {
            throw new IllegalArgumentException("data may not be null.");
        }

        mContext = context;
        mTag  = tag;
        mContentType = contentType;
        mSrc  = src;
        mData = data;
        mSize = data.length;
        mMediaType   = mediatype;
        mMediaActions = new ArrayList<MediaAction>();
    }

    public int getBegin() {
        return mBegin;
    }

    public void setBegin(int begin) {
        mBegin = begin;
        notifyModelChanged(true);
    }

    public int getDuration() {
        return mDuration;
    }

    public void setDuration(int duration) {
        if (isPlayable() && (duration < 0)) {
            // 'indefinite' duration, we should try to find its exact value;
            try {
                initMediaDuration();
            } catch (AdException e) {
                // On error, keep default duration.
                Log.e(TAG, e.getMessage(), e);
                return;
            }
        } else {
            mDuration = duration;
        }
        notifyModelChanged(true);
    }

    public String getTag() {
        return mTag;
    }

    public String getContentType() {
        return mContentType;
    }

    /**
     * Get the URI of the media.
     *
     * @return The URI of the media.
     */
    public Uri getUri() {
        return mUri;
    }

    public byte[] getData() {
        if (mData != null) {
            byte[] data = new byte[mData.length];
            System.arraycopy(mData, 0, data, 0, mData.length);
            return data;
        }
        return null;
    }

    /**
     * @param uri the mUri to set
     */
    void setUri(Uri uri) {
        mUri = uri;
    }

    /**
     * @return the mSrc
     */
    public String getMediaType() {
        return mMediaType;
    }
    public String getSrc(){
    	return mSrc;
    }
    /**
     * @return the mFill
     */
    public short getFill() {
        return mFill;
    }

    /**
     * @param fill the mFill to set
     */
    public void setFill(short fill) {
        mFill = fill;
        notifyModelChanged(true);
    }

    /**
     * @return whether the media is resizable or not. For instance, a picture can be resized
     * to smaller dimensions or lower resolution. Other media, such as video and sounds, aren't
     * currently able to be resized.
     */
    public boolean getMediaResizable() {
        return mMediaResizeable;
    }

    /**
     * @return the size of the attached media
     */
    public int getMediaSize() {
        return mSize;
    }

    public boolean isText() {
        return mContentType.equals(MediaHelper.MEDIA_TAG_TEXT);
    }

    public boolean isImage() {
        return mContentType.equals(MediaHelper.MEDIA_TAG_IMAGE);
    }

    public boolean isVideo() {
        return mContentType.equals(MediaHelper.MEDIA_TAG_VIDEO);
    }

    public boolean isAudio() {
        return mContentType.equals(MediaHelper.MEDIA_TAG_AUDIO);
    }
    public boolean isPpt(){
    	return mContentType.equals(MediaHelper.MEDIA_TAG_PPT);
    }
    public boolean isLive(){
    	return mContentType.equals(MediaHelper.MEIDA_TAG_LIVE);
    }
    @SuppressLint("NewApi")
	protected void initMediaDuration() throws AdException {
        if (mUri == null) {
            throw new IllegalArgumentException("Uri may not be null.");
        }

        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        int duration = 0;
        try {
            retriever.setDataSource(mContext, mUri);
            String dur = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            if (dur != null) {
                duration = Integer.parseInt(dur);
            }
            mDuration = duration;
        } catch (Exception ex) {
            Log.e(TAG, "MediaMetadataRetriever failed to get duration for " + mUri.getPath(), ex);
            throw new AdException(ex);
        } finally {
            retriever.release();
        }
    }

    private void initMediaSize() throws AdException {
        ContentResolver cr = mContext.getContentResolver();
        InputStream input = null;
        try {
            input = cr.openInputStream(mUri);
            if (input instanceof FileInputStream) {
                // avoid reading the whole stream to get its length
                FileInputStream f = (FileInputStream) input;
                mSize = (int) f.getChannel().size();
                // sometimes mSize will be zero here. It's tempting to count the bytes as the code
                // does below, but that turns out to be very slow. We'll deal with a zero size
                // when we resize the media.
            } else {
                while (-1 != input.read()) {
                    mSize++;
                }
            }

        } catch (IOException e) {
            // Ignore
            Log.e(TAG, "IOException caught while opening or reading stream", e);
            if (e instanceof FileNotFoundException) {
                throw new AdException(e.getMessage());
            }
        } finally {
            if (null != input) {
                try {
                    input.close();
                } catch (IOException e) {
                    // Ignore
                    Log.e(TAG, "IOException caught while closing stream", e);
                }
            }
        }
    }

    public static boolean isMmsUri(Uri uri) {
        return uri.getAuthority().startsWith("mms");
    }

    public int getSeekTo() {
        return mSeekTo;
    }

    public void appendAction(MediaAction action) {
        mMediaActions.add(action);
    }

    public MediaAction getCurrentAction() {
        if (0 == mMediaActions.size()) {
            return MediaAction.NO_ACTIVE_ACTION;
        }
        return mMediaActions.remove(0);
    }

    protected boolean isPlayable() {
        return false;
    }

    protected void pauseMusicPlayer() {
        Log.d(TAG, "pauseMusicPlayer");     

        Intent i = new Intent(MUSIC_SERVICE_ACTION);
        i.putExtra("command", "pause");
        mContext.sendBroadcast(i);
    }

    /**
     * If the attached media is resizeable, resize it to fit within the byteLimit. Save the
     * new part in the pdu.
     * @param byteLimit the max size of the media attachment
     * @throws MmsException
     */
    protected void resizeMedia(int byteLimit, long messageId) throws AdException {
    }

}
