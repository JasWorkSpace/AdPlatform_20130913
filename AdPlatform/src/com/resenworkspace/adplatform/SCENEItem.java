package com.resenworkspace.adplatform;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import android.text.TextUtils;

import com.ResenWorkSpace.ResenWorkSpace.Log;
import com.resenworkspace.MediaModel.AudioModel;
import com.resenworkspace.MediaModel.ImageModel;
import com.resenworkspace.MediaModel.LiveModel;
import com.resenworkspace.MediaModel.PptModel;
import com.resenworkspace.MediaModel.TextModel;
import com.resenworkspace.MediaModel.VideoModel;
import com.resenworkspace.data.XML.ElementTime;
import com.resenworkspace.data.XML.Event;
import com.resenworkspace.data.XML.IModelChangedObserver;
import com.resenworkspace.data.XML.MediaModel;
import com.resenworkspace.data.XML.Model;


public class SCENEItem extends Model implements List<MediaModel>{
     
	 private String TAG = "SCENEItem";
	 private final ArrayList<MediaModel> mMedia = new ArrayList<MediaModel>();//It always only one item.
	 private static final int DEFAULT_SLIDE_DURATION = 5000;
	 
	 private MediaModel mText;
	 private MediaModel mImage;
	 private MediaModel mAudio;
	 private MediaModel mVideo;
	 private MediaModel mPpt;
	 private MediaModel mLive;
	 
	 private boolean mCanAddMediaModel = true;
	 
	 
	 private int     mDuration;
	 private boolean mVisible = true;
	 private short   mFill;
	 private int     mSCENEItemSize;
	 private SCENE   mParent;
	    
	 public SCENEItem(SCENE scene) {
	      this(DEFAULT_SLIDE_DURATION, scene);
	 }

	 public SCENEItem(int duration, SCENE scene) {
	      mDuration = duration;
	      mParent   = scene;
	 }
	 public SCENEItem(int duration, ArrayList<MediaModel> mediaList) {
	      mDuration = duration;
	      int maxDur = 0;
	      for (MediaModel media : mediaList) {
	          internalAdd(media);
	          int mediaDur = media.getDuration();
	          if (mediaDur > maxDur) {
	              maxDur = mediaDur;
	          }
	      }
	      updateDuration(maxDur);
	  } 
	 /*Interface for mediamodel*/
    public boolean hasText() {
        return mText != null;
    }
    public boolean hasImage() {
        return mImage != null;
    }
    public boolean hasAudio() {
        return mAudio != null;
    }
    public boolean hasVideo() {
        return mVideo != null;
    }    
    public boolean hasPpt(){
    	return mPpt != null;
    }    
    public boolean hasLive(){
    	return mLive != null;
    }
    
    public boolean removeText() {
        return remove(mText);
    }
    public boolean removeImage() {
        return remove(mImage);
    }
    public boolean removeAudio() {
        boolean result = remove(mAudio);
        resetDuration();
        return result;
    }
    public boolean removeVideo() {
        boolean result = remove(mVideo);
        resetDuration();
        return result;
    }    
    public boolean removePpt(){
    	boolean result = remove(mPpt);
    	resetDuration();
        return result;
    }
    public boolean removeLive(){
    	boolean result = remove(mLive);
    	resetDuration();
        return result;
    }
    
    public TextModel getText() {
        return (TextModel) mText;
    }
    public ImageModel getImage() {
        return (ImageModel) mImage;
    }
    public AudioModel getAudio() {
        return (AudioModel) mAudio;
    }
    public VideoModel getVideo() {
        return (VideoModel) mVideo;
    }    
    public PptModel getPpt(){
    	return (PptModel) mPpt;
    }    
    public LiveModel getLive(){
    	return (LiveModel) mLive;
    }
    
    
	 public void resetDuration() {
	        // If we remove all the objects that have duration, reset the slide back to its
	        // default duration. If we don't do this, if the user replaces a 10 sec video with
	        // a 3 sec audio, the duration will remain at 10 sec (see the way updateDuration() below
	        // works).
	        if (!hasAudio() && !hasVideo()) {
	            mDuration = DEFAULT_SLIDE_DURATION;
	        }
	  }
	  public void updateDuration(int duration) {
	        if (duration <= 0) {
	            return;
	        }
	        if ((duration > mDuration)
	                || (mDuration == DEFAULT_SLIDE_DURATION)) {
	            mDuration = duration;
	        }
	    }
	 private void internalAdd(MediaModel media) throws IllegalStateException {
	        if (media == null) {
	            // Don't add null value into the list.
	            return;
	        }
	        if (media.isText()) {
	            String contentType = media.getContentType();
	            if (TextUtils.isEmpty(contentType) || MediaHelper.MEDIA_TAG_TEXT.equals(contentType)) {
	                internalAddOrReplace(mText, media);
	                mText = media;
	            } else {
	                Log.i(TAG, "[SCENEItem] content type " + media.getContentType() +
	                        " isn't supported (as text)");
	            }
	        } else if (media.isImage()) {
	            if (mCanAddMediaModel) {
	                internalAddOrReplace(mImage, media);
	                mImage = media;
	                mCanAddMediaModel = false;
	            } else {
	                Log.w(TAG, "[SCENEItem] content type " + media.getContentType() +
	                    " - can't add image in this state");
	            }
	        } else if (media.isAudio()) {
	            if (mCanAddMediaModel) {
	                internalAddOrReplace(mAudio, media);
	                mAudio = media;
	                mCanAddMediaModel = false;
	            } else {
	                Log.w(TAG, "[SCENEItem] content type " + media.getContentType() +
	                    " - can't add audio in this state");
	            }
	        } else if (media.isVideo()) {
	            if (mCanAddMediaModel) {
	                internalAddOrReplace(mVideo, media);
	                mVideo = media;
	                mCanAddMediaModel = false;
	            } else {
	                Log.w(TAG, "[SCENEItem] content type " + media.getContentType() +
	                    " - can't add video in this state");
	            }
	        } else if (media.isPpt()) {
	            if (mCanAddMediaModel) {
	                internalAddOrReplace(mPpt, media);
	                mPpt = media;
	                mCanAddMediaModel = false;
	            } else {
	                Log.w(TAG, "[SCENEItem] content type " + media.getContentType() +
	                    " - can't add ppt in this state");
	            }
	        } else if (media.isLive()) {
	            if (mCanAddMediaModel) {
	                internalAddOrReplace(mLive, media);
	                mLive = media;
	                mCanAddMediaModel = false;
	            } else {
	                Log.w(TAG, "[SCENEItem] content type " + media.getContentType() +
	                    " - can't add live in this state");
	            }
	        }
	    }
	 
	 private void internalAddOrReplace(MediaModel old, MediaModel media) {
	        // If the media is resizable, at this point consider it to be zero length.
	        // Just before we send the slideshow, we take the remaining space in the
	        // slideshow and equally allocate it to all the resizeable media items and resize them.
	        int addSize = media.getMediaResizable() ? 0 : media.getMediaSize();
	        int removeSize;
	        if (old == null) {
	            if (null != mParent) {
	                mParent.checkMessageSize(addSize);
	            }
	            mMedia.add(media);
	            increaseSCENEItemSize(addSize);
	            increaseSCENESize(addSize);
	        } else {
	            removeSize = old.getMediaResizable() ? 0 : old.getMediaSize();
	            if (addSize > removeSize) {
	                if (null != mParent) {
	                    mParent.checkMessageSize(addSize - removeSize);
	                }
	                increaseSCENEItemSize(addSize - removeSize);
	                increaseSCENESize(addSize - removeSize);
	            } else {
	                decreaseSCENEItemSize(removeSize - addSize);
	                decreaseSCENESize(removeSize - addSize);
	            }
	            mMedia.set(mMedia.indexOf(old), media);
	            old.unregisterAllModelChangedObservers();
	        }
	        for (IModelChangedObserver observer : mModelChangedObservers) {
	            media.registerModelChangedObserver(observer);
	        }
	    }

	    private boolean internalRemove(Object object) {
	        if (mMedia.remove(object)) {
	            if (object instanceof TextModel) {
	                mText = null;
	            } else if (object instanceof ImageModel) {
	                mImage = null;
	            } else if (object instanceof AudioModel) {
	                mAudio = null;
	            } else if (object instanceof VideoModel) {
	                mVideo = null;
	            } else if (object instanceof VideoModel) {
	            	mPpt   = null;
	            } else if (object instanceof VideoModel) {
	                mLive  = null;
	            }
	            mCanAddMediaModel = true;
	            // If the media is resizable, at this point consider it to be zero length.
	            // Just before we send the slideshow, we take the remaining space in the
	            // slideshow and equally allocate it to all the resizeable media items and resize them.
	            int decreaseSize = ((MediaModel) object).getMediaResizable() ? 0
	                                        : ((MediaModel) object).getMediaSize();
	            decreaseSCENEItemSize(decreaseSize);
	            decreaseSCENESize(decreaseSize);

	            ((Model) object).unregisterAllModelChangedObservers();

	            return true;
	        }

	        return false;
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
        notifyModelChanged(true);
    }
    /**
     * @return the mDuration
     */
    public int getDuration() {
        return mDuration;
    }

    /**
     * @param duration the mDuration to set
     */
    public void setDuration(int duration) {
        mDuration = duration;
        notifyModelChanged(true);
    }
	 public int getSCENEItemSize() {
	        return mSCENEItemSize;
	 }
	 /*Interface to increase or decrease */
	 public void increaseSCENEItemSize(int increaseSize) {
	     if (increaseSize > 0) {
	         mSCENEItemSize += increaseSize;
	     }
	 }
	 public void decreaseSCENEItemSize(int decreaseSize) {
	     if (decreaseSize > 0) {
	         mSCENEItemSize -= decreaseSize;
	     }
	 }
	 public void decreaseSCENESize(int decreaseSize) {
	        if ((decreaseSize > 0) && (null != mParent)) {
	            int size = mParent.getCurrentSCENESize();
	            size -= decreaseSize;
	            mParent.setCurrentSCENESize(size);
	        }
	    }
	 public void increaseSCENESize(int increaseSize) {
	        if ((increaseSize > 0) && (null != mParent)) {
	            int size = mParent.getCurrentSCENESize();
	            size += increaseSize;
	            mParent.setCurrentSCENESize(size);
	        }
	 }
	@Override
	public boolean add(MediaModel object) {
		// TODO Auto-generated method stub
		internalAdd(object);
        notifyModelChanged(true);
        return true;
	}
	@Override
	public void add(int location, MediaModel object) {
		// TODO Auto-generated method stub
		 throw new UnsupportedOperationException("Operation not supported.");
	}
	@Override
	public boolean addAll(Collection<? extends MediaModel> arg0) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Operation not supported.");
	}
	@Override
	public boolean addAll(int arg0, Collection<? extends MediaModel> arg1) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Operation not supported.");
	}
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		if (mMedia.size() > 0) {
            for (MediaModel media : mMedia) {
                media.unregisterAllModelChangedObservers();
                int decreaseSize = media.getMediaSize();
                decreaseSCENEItemSize(decreaseSize);
                decreaseSCENESize(decreaseSize);
            }
            mMedia.clear();
            
            mText  = null;
            mImage = null;
            mAudio = null;
            mVideo = null;
            mPpt   = null;
            mLive  = null;
            
            mCanAddMediaModel = true;
            
            notifyModelChanged(true);
        }
	}
	@Override
	public boolean contains(Object object) {
		// TODO Auto-generated method stub
		return mMedia.contains(object);
	}
	@Override
	public boolean containsAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return mMedia.containsAll(arg0);
	}
	@Override
	public MediaModel get(int location) {
		// TODO Auto-generated method stub
		if (mMedia.size() == 0) {
	        return null;
	    }

	    return mMedia.get(location);
	}
	@Override
	public int indexOf(Object object) {
		// TODO Auto-generated method stub
		return mMedia.indexOf(object);
	}
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return mMedia.isEmpty();
	}
	@Override
	public Iterator<MediaModel> iterator() {
		// TODO Auto-generated method stub
		return mMedia.iterator();
	}
	@Override
	public int lastIndexOf(Object object) {
		// TODO Auto-generated method stub
		return mMedia.lastIndexOf(object);
	}
	@Override
	public ListIterator<MediaModel> listIterator() {
		// TODO Auto-generated method stub
		return mMedia.listIterator();
	}
	@Override
	public ListIterator<MediaModel> listIterator(int location) {
		// TODO Auto-generated method stub
		return mMedia.listIterator(location);
	}
	@Override
	public MediaModel remove(int location) {
		// TODO Auto-generated method stub
		MediaModel media = mMedia.get(location);
        if ((media != null) && internalRemove(media)) {
            notifyModelChanged(true);
        }
        return media;
	}
	@Override
	public boolean remove(Object object) {
		// TODO Auto-generated method stub
		if ((object != null) && (object instanceof MediaModel)
                && internalRemove(object)) {
            notifyModelChanged(true);
            return true;
        }
        return false;
	}
	@Override
	public boolean removeAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean retainAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Operation not supported.");
	}
	@Override
	public MediaModel set(int location, MediaModel object) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Operation not supported.");
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return mMedia.size();
	}
	@Override
	public List<MediaModel> subList(int start, int end) {
		// TODO Auto-generated method stub
		return mMedia.subList(start, end);
	}
	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return mMedia.toArray();
	}
	@Override
	public <T> T[] toArray(T[] array) {
		// TODO Auto-generated method stub
		return mMedia.toArray(array);
	}
	@Override
    protected void registerModelChangedObserverInDescendants(
            IModelChangedObserver observer) {
        for (MediaModel media : mMedia) {
            media.registerModelChangedObserver(observer);
        }
    }

    @Override
    protected void unregisterModelChangedObserverInDescendants(
            IModelChangedObserver observer) {
        for (MediaModel media : mMedia) {
            media.unregisterModelChangedObserver(observer);
        }
    }

    @Override
    protected void unregisterAllModelChangedObserversInDescendants() {
        for (MediaModel media : mMedia) {
            media.unregisterAllModelChangedObservers();
        }
    }
 // EventListener Interface
    public void handleEvent(Event evt) {
        if (evt.getType().equals(MediaControl.START_EVENT)) {
            Log.i(TAG, "Start to play slide: " + this);
            mVisible = true;
        } else if (mFill != ElementTime.FILL_FREEZE) {
            Log.i(TAG, "Stop playing slide: " + this);
            mVisible = false;
        }
        notifyModelChanged(false);
    }

}
