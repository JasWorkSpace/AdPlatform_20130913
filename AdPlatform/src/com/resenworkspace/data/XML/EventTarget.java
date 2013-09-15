package com.resenworkspace.data.XML;


public interface EventTarget {
	
    public void addEventListener(String type, 
                                 EventListener listener, 
                                 boolean useCapture);

 
    public void removeEventListener(String type, 
                                    EventListener listener, 
                                    boolean useCapture);

   
    public boolean dispatchEvent(Event evt)
                                 throws EventException;
}
