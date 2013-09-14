package com.resenworkspace.adplatform;

public interface XMLTAG {

	 public final static String STARTTAG = "AdPub"; //the start of the xml parser
	 
	 //about file
	 public class FILE{
		 public final static String FILE_TAG   = "files";
		
		 public class ITEM{
			 public final static String ITEM_TAG   = "item";
			 public final static String ITEM_ID    = "id";
			 public final static String ITEM_NAME  = "name";
			 public final static String ITEM_DELAY = "delay";
			 public final static String ITEM_TYPE  = "type";
			 public final static String ITEM_PATH  = "path";
			 public final static String ITEM_MSG   = "msg";
		 }
	 }
	 
	 //about option
	 public class OPTION{
		 public final static String OPTION_TAG   = "option";
		 
		 public class BKIMG{
			 public final static String BKIMG_TAG   = "bkImg";
			 public final static String BKIMG_ID    = "id";
		 }
	 }
	 
	 //about Scence
	 public class SCENE{
		 public final static String SCENE_TAG     = "scence";
		 public final static String SCENE_ID      = "id";
		 public final static String SCENE_NAME    = "name";
		 public final static String SCENE_BKIMGID = "bgImgId";
		 
		 public class RECT{
			 public final static String RECT_TAG    = "rect";
			 public final static String RECT_X      = "x";
			 public final static String RECT_Y      = "y";
			 public final static String RECT_WIDTH  = "cx";
			 public final static String RECT_HEIGHT = "cy";
			 
			 public class ITEM{
				 public final static String ITEM_TAG    = "item";
				 public final static String ITEM_ID     = "id";
				 public final static String ITEM_NAME   = "name";
				 public final static String ITEM_TYPE   = "type";
				 public final static String ITEM_START  = "start";
				 public final static String ITEM_END    = "end";
				 public final static String ITEM_X      = "x";
				 public final static String ITEM_Y      = "y";
				 public final static String ITEM_WIDTH  = "cx";
				 public final static String ITEM_HEIGHT = "cy";
			 }
		 }
	 }
	 
}
