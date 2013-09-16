package com.resenworkspace.adplatform;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.resenworkspace.data.XML.ContentRestriction;
import com.resenworkspace.data.XML.ContentRestrictionException;
import com.resenworkspace.data.XML.ContentRestrictionFactory;
import com.resenworkspace.data.XML.IModelChangedObserver;
import com.resenworkspace.data.XML.Model;

import android.content.Context;

public class SCENE extends Model implements List<SCENEItem> ,IModelChangedObserver{
	
	private Context mContext;
	private int     mCurrentSCENESize = 0; //it use to control mediasize not let the size add to big
	
	private final ArrayList<SCENEItem> mSCENCE = new ArrayList<SCENEItem>();
	
	
    public void checkMessageSize(int increaseSize) throws ContentRestrictionException {
	        ContentRestriction cr = ContentRestrictionFactory.getContentRestriction();
	        cr.checkMessageSize(mCurrentSCENESize, increaseSize, mContext.getContentResolver());
	}
    public void increaseMessageSize(int increaseSize) {
        if (increaseSize > 0) {
        	mCurrentSCENESize += increaseSize;
        }
    }
    public void decreaseMessageSize(int decreaseSize) {
        if (decreaseSize > 0) {
        	mCurrentSCENESize -= decreaseSize;
        }
    }
	@Override
	public boolean add(SCENEItem object) {
		// TODO Auto-generated method stub
		int increaseSize = object.getSCENEItemSize();
        checkMessageSize(increaseSize);
        if ((object != null) && mSCENCE.add(object)) {
            increaseMessageSize(increaseSize);
            object.registerModelChangedObserver(this);
            for (IModelChangedObserver observer : mModelChangedObservers) {
                object.registerModelChangedObserver(observer);
            }
            notifyModelChanged(true);
            return true;
        }
        return false;
	}

	@Override
	public void add(int location, SCENEItem object) {
		// TODO Auto-generated method stub
		 if (object != null) {
	            int increaseSize = object.getSCENEItemSize();
	            checkMessageSize(increaseSize);
	            mSCENCE.add(location, object);
	            increaseMessageSize(increaseSize);
	            object.registerModelChangedObserver(this);
	            for (IModelChangedObserver observer : mModelChangedObservers) {
	                object.registerModelChangedObserver(observer);
	            }
	            notifyModelChanged(true);
	     }
	}

	@Override
	public boolean addAll(Collection<? extends SCENEItem> arg0) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Operation not supported.");
	}

	@Override
	public boolean addAll(int arg0, Collection<? extends SCENEItem> arg1) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Operation not supported.");
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		if(mSCENCE.size()>0){
			for(SCENEItem Item :mSCENCE){
				Item.unregisterAllModelChangedObservers();
				 for (IModelChangedObserver observer : mModelChangedObservers) {
					 Item.unregisterModelChangedObserver(observer);
	             }
			}
		}
		mCurrentSCENESize = 0;
		mSCENCE.clear();
        notifyModelChanged(true);		
	}

	@Override
	public boolean contains(Object object) {
		// TODO Auto-generated method stub
		return mSCENCE.contains(object);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		 return mSCENCE.containsAll(arg0);
	}

	@Override
	public SCENEItem get(int location) {
		// TODO Auto-generated method stub
		return (location >= 0 && location < mSCENCE.size()) ? mSCENCE.get(location) : null;
	}

	@Override
	public int indexOf(Object object) {
		// TODO Auto-generated method stub
		return mSCENCE.indexOf(object);
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return mSCENCE.isEmpty();
	}

	@Override
	public Iterator<SCENEItem> iterator() {
		// TODO Auto-generated method stub
		return mSCENCE.iterator();
	}

	@Override
	public int lastIndexOf(Object object) {
		// TODO Auto-generated method stub
		return mSCENCE.lastIndexOf(object);
	}

	@Override
	public ListIterator<SCENEItem> listIterator() {
		// TODO Auto-generated method stub
		return mSCENCE.listIterator();
	}

	@Override
	public ListIterator<SCENEItem> listIterator(int location) {
		// TODO Auto-generated method stub
		 return mSCENCE.listIterator(location);
	}

	@Override
	public SCENEItem remove(int location) {
		// TODO Auto-generated method stub
		SCENEItem scence = mSCENCE.remove(location);
        if (scence != null) {
            decreaseMessageSize(scence.getSCENEItemSize());
            scence.unregisterAllModelChangedObservers();
            notifyModelChanged(true);
        }
        return scence;
	}

	@Override
	public boolean remove(Object object) {
		// TODO Auto-generated method stub
		if ((object != null) && mSCENCE.remove(object)) {
			SCENEItem scence = (SCENEItem) object;
            decreaseMessageSize(scence.getSCENEItemSize());
            scence.unregisterAllModelChangedObservers();
            notifyModelChanged(true);
            return true;
        }
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		 throw new UnsupportedOperationException("Operation not supported.");
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		 throw new UnsupportedOperationException("Operation not supported.");
	}

	@Override
	public SCENEItem set(int location, SCENEItem object) {
		// TODO Auto-generated method stub
		SCENEItem scence = mSCENCE.get(location);
        if (null != object) {
            int removeSize = 0;
            int addSize = object.getSCENEItemSize();
            if (null != scence) {
                removeSize = scence.getSCENEItemSize();
            }
            if (addSize > removeSize) {
                checkMessageSize(addSize - removeSize);
                increaseMessageSize(addSize - removeSize);
            } else {
                decreaseMessageSize(removeSize - addSize);
            }
        }
        scence =  mSCENCE.set(location, object);
        if (scence != null) {
        	scence.unregisterAllModelChangedObservers();
        }
        if (object != null) {
            object.registerModelChangedObserver(this);
            for (IModelChangedObserver observer : mModelChangedObservers) {
                object.registerModelChangedObserver(observer);
            }
        }
        notifyModelChanged(true);
        return scence;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return mSCENCE.size();
	}

	@Override
	public List<SCENEItem> subList(int start, int end) {
		// TODO Auto-generated method stub
		return mSCENCE.subList(start, end);
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return mSCENCE.toArray();
	}

	@Override
	public <T> T[] toArray(T[] array) {
		// TODO Auto-generated method stub
		return mSCENCE.toArray(array);
	}

	@Override
	public void onModelChanged(Model model, boolean dataChanged) {
		// TODO Auto-generated method stub
		if (dataChanged){
			
		}
	}
	public int getCurrentSCENESize() {
		// TODO Auto-generated method stub
		return mCurrentSCENESize;
	}
	public void setCurrentSCENESize(int size) {
		// TODO Auto-generated method stub
		mCurrentSCENESize = size;
	}
	
}
