package com.actonica.fitstore.Downloader;

/**
 * Created by ilgar on 03.07.2016.
 */
public interface IDownloader {
    void updateDownloadProgress(int programId, int progress);
    void setDownloadFinished(int programId, boolean isSuccess);
}
