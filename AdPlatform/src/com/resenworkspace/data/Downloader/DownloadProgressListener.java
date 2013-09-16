package com.resenworkspace.data.Downloader;

public interface DownloadProgressListener {
	public void onDownloadSize(int size);
	public void onDoenloadState(int state);
}
