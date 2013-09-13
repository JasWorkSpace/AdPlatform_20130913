package com.resenworkspace.data.XML;

public class SCENEItem extends RegionMediaModel{
  
	 private int mSlideSize;
	
	 public int getSlideSize() {
	        return mSlideSize;
	 }
	 public void increaseSlideSize(int increaseSize) {
	     if (increaseSize > 0) {
	         mSlideSize += increaseSize;
	     }
	 }

	 public void decreaseSlideSize(int decreaseSize) {
	     if (decreaseSize > 0) {
	         mSlideSize -= decreaseSize;
	     }
	 }
}
