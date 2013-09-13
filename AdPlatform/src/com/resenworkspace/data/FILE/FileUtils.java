package com.resenworkspace.data.FILE;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.os.Environment;

/** File copy/delete/move */
public class FileUtils {
    private static final String TAG = "FileUtils";

    /** Delete files or folders */
    public static boolean deleteFiles(final String path) {
        boolean ret = true;
        File f = new File(path);

        if (!f.isDirectory()) {
            ret = f.delete() == false || ret == false ? false : true;
        }
        else {
            File[] files = f.listFiles();
            if (files != null) {
                // Get folders list
                for (int i = 0; i < files.length; i++) {
                    ret = deleteFiles(files[i].getPath()) == false || ret == false ? false : true;
                    if (ret == false)
                        return false;
                }
            }
            ret = f.delete() == false || ret == false ? false : true;
        }

        return ret;
    }

    /** Copy folders */
    private static boolean copyDir(File srcDir, File dstDir) {
        if (dstDir.exists()) {
            if (dstDir.isDirectory() == false) {
                return false;
            }
        } else {
            if (dstDir.mkdirs() == false) {
                return false;
            }
        }
        if (dstDir.canWrite() == false) {
            return false;
        }
        File[] files = srcDir.listFiles();
        if (files == null) {
            return false;
        }        
        for (int i = 0; i < files.length; i++) {
            File dstFile = new File(dstDir, files[i].getName());
            if (files[i].isDirectory()) {
                copyDir(files[i], dstFile);
            } else {
                copyFile(files[i], dstFile);
            }
        }
        
        return true;
    }

    /** Move files or folders */
    public static boolean moveFiles(File srcFile, File dstFile) {
        boolean ret = true;
        ret = srcFile.compareTo(dstFile) != 0
                && (srcFile.renameTo(dstFile) || copyFiles(srcFile, dstFile));
        if (ret) {
            deleteFiles(srcFile.getPath());
        }
        return ret;
    }

    /** Copy files or folders */
    public static boolean copyFiles(File srcFile, File dstFile) {
        boolean ret = true;
        if (srcFile.getParent().equals(dstFile)) {
            return false;
        }        
        if (srcFile.isDirectory()) {
            if (dstFile.getPath().indexOf(srcFile.getPath()) == 0) {
                return false;
            }
            else {
                if (copyDir(srcFile, dstFile) == false) {
                    return false;
                }
            }
        }
        else {
            ret = copyFile(srcFile, dstFile);
        }
        return ret;
    }

    /** Copy binary file */
    public static boolean copyFile(File srcFile, File dstFile) {
        try {
            InputStream in = new FileInputStream(srcFile);
            if (dstFile.exists()) {
                dstFile.delete();
            }
            OutputStream out = new FileOutputStream(dstFile);
            try {
                int cnt;
                byte[] buf = new byte[4096];
                while ((cnt = in.read(buf)) >= 0) {
                    out.write(buf, 0, cnt);
                }
            } finally {
                out.close();
                in.close();
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    
    public static boolean fileExist(String fileName){
        boolean ret = false;        
        File f = new File(fileName);
        if (f.exists()) {
            ret = true;
        }
        return ret;
    }
    
    private static String getMIMEType(String fileName){
    	String type = "";
    	String ext = fileName.substring(fileName.lastIndexOf(".")
                + 1, fileName.length()).toLowerCase();
            if (ext.equals("jpg") || ext.equals("jpeg") || ext.equals("gif")
                || ext.equals("png") || ext.equals("bmp")) {
                type = "image/*";
            }
            else if (ext.equals("mp3") || ext.equals("amr") || ext.equals("wma")
                || ext.equals("aac") || ext.equals("m4a") || ext.equals("mid")
                || ext.equals("xmf") || ext.equals("ogg") || ext.equals("wav")
                || ext.equals("qcp") || ext.equals("awb")) {
                type = "audio/*";
            }
            else if (ext.equals("3gp") || ext.equals("avi") || ext.equals("mp4")
                || ext.equals("3g2") || ext.equals("wmv") || ext.equals("divx")
                || ext.equals("mkv") || ext.equals("webm") || ext.equals("ts")) {
                type = "video/*";
            }
            else if (ext.equals("apk")) {
                type = "application/vnd.android.package-archive";
            }
            else if (ext.equals("vcf")) {
                type = "text/x-vcard";
            }
            else if (ext.equals("txt")) {
                type = "text/plain";
            }
            else if(ext.equals("ppt")){
            	type = "text/plain";
            }
            else {
                type = "*/*";
            }
            return type;
    }
    
    public static String getMIMEType(File f) {
    	return getMIMEType(f.getName());        
    }
    
}
