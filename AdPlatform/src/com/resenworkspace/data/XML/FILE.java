package com.resenworkspace.data.XML;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class FILE implements List<FILEItem>{
    
    public enum FILETYPE{
    	UNKNOW("unknow"),
    	PIC   ("pic"),
    	VIDEO ("video"),
    	AUDIO ("audio"),
    	TXT   ("txt"),
    	PPT   ("ppt"),
    	LIVE  ("live");
    	private String TYPE;
    	FILETYPE(String type){
    		TYPE = type;
    	}
    	public String toString(){
    		return TYPE;
    	}
    }
    public static boolean isAviableType(String type){
    	try{
    	    getFILETYPE(type);
    	    return true;
    	}catch(ContentRestrictionException e){
    		e.printStackTrace();
    	}
    	return false;
    }
    public static FILETYPE getFILETYPE(String type){
    	if((FILETYPE.PIC.toString().equals(type)))return FILETYPE.PIC;
    	else if((FILETYPE.VIDEO.toString().equals(type)))return FILETYPE.VIDEO;
    	else if((FILETYPE.AUDIO.toString().equals(type)))return FILETYPE.AUDIO;
    	else if((FILETYPE.TXT.toString().equals(type)))return FILETYPE.TXT;
    	else if((FILETYPE.PPT.toString().equals(type)))return FILETYPE.PPT;
    	else if((FILETYPE.LIVE.toString().equals(type)))return FILETYPE.LIVE;
    	else throw new ContentRestrictionException("UnSuport type :"+type);
    }
	@Override
	public boolean add(FILEItem object) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void add(int location, FILEItem object) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean addAll(Collection<? extends FILEItem> arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean addAll(int arg0, Collection<? extends FILEItem> arg1) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean contains(Object object) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean containsAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public FILEItem get(int location) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int indexOf(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Iterator<FILEItem> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int lastIndexOf(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public ListIterator<FILEItem> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ListIterator<FILEItem> listIterator(int location) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public FILEItem remove(int location) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean remove(Object object) {
		// TODO Auto-generated method stub
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
		return false;
	}
	@Override
	public FILEItem set(int location, FILEItem object) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List<FILEItem> subList(int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> T[] toArray(T[] array) {
		// TODO Auto-generated method stub
		return null;
	}
    
}
