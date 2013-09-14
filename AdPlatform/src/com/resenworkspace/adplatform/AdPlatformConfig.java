package com.resenworkspace.adplatform;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.ResenWorkSpace.ResenWorkSpace.Log;

import android.content.Context;
import android.content.res.XmlResourceParser;

public class AdPlatformConfig {
	
    private static String TAG = "AdPlatformConfig";
	private static int mMAXAdSize = 0;
	private static int mMaxHeight = 0;
	private static int mMaxWidth  = 0;
	public static int getMaxAdSize() {
		// TODO Auto-generated method stub
		return mMAXAdSize;
	}
    
	public static void init(Context context) {
        loadMmsSettings(context);
    }
	private static void loadMmsSettings(Context context) {
        XmlResourceParser parser = context.getResources().getXml(R.xml.adplatformconfig);
        try {
            beginDocument(parser, "adplatform_config");
            while (true) {
                nextElement(parser);
                String tag = parser.getName();
                if (tag == null) {
                    break;
                }
                String name  = parser.getAttributeName(0);
                String value = parser.getAttributeValue(0);
                String text  = null;
                if (parser.next() == XmlPullParser.TEXT) {
                    text = parser.getText();
                }                
                Log.i(TAG, "tag: " + tag + " value: " + value + " - " + text);            
                if ("name".equalsIgnoreCase(name)) {
                    if ("bool".equals(tag)) {
                        // bool config tags go here
                        if ("enabledMMS".equalsIgnoreCase(value)) {
                           // mMmsEnabled = "true".equalsIgnoreCase(text) ? 1 : 0;
                        } 
                    } else if ("int".equals(tag)) {
                        // int config tags go here
                        if ("AdMaxSize".equalsIgnoreCase(value)) {
                        	mMAXAdSize = Integer.parseInt(text);
                        }else if ("AdMaxWidth".equalsIgnoreCase(value)) {
                        	mMaxWidth = Integer.parseInt(text);
                        }else if ("AdMaxHeight".equalsIgnoreCase(value)) {
                        	mMaxHeight = Integer.parseInt(text);
                        }                         
                    } else if ("string".equals(tag)) {
                        // string config tags go here
                        if ("userAgent".equalsIgnoreCase(value)) {
                            //mUserAgent = text;
                        }
                    }
                }
            }
        } catch (XmlPullParserException e) {
            Log.e(TAG, "loadAdSettings caught ", e);
        } catch (NumberFormatException e) {
            Log.e(TAG, "loadAdSettings caught ", e);
        } catch (IOException e) {
            Log.e(TAG, "loadAdSettings caught ", e);
        } finally {
            parser.close();
        }
    }
	public static final void beginDocument(XmlPullParser parser, String firstElementName) throws XmlPullParserException, IOException
    {
        int type;
        while ((type=parser.next()) != parser.START_TAG
                   && type != parser.END_DOCUMENT) {
            ;
        }
        if (type != parser.START_TAG) {
            throw new XmlPullParserException("No start tag found");
        }
        if (!parser.getName().equals(firstElementName)) {
            throw new XmlPullParserException("Unexpected start tag: found " + parser.getName() +
                    ", expected " + firstElementName);
        }
    }

    public static final void nextElement(XmlPullParser parser) throws XmlPullParserException, IOException
    {
        int type;
        while ((type=parser.next()) != parser.START_TAG
                   && type != parser.END_DOCUMENT) {
            ;
        }
    }

	public static int getMaxHeight() {
		// TODO Auto-generated method stub
		return mMaxHeight;
	}

	public static int getMaxWidth() {
		// TODO Auto-generated method stub		
		return mMaxWidth;
	}


}
