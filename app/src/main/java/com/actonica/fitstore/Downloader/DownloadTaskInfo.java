package com.actonica.fitstore.Downloader;

import com.actonica.fitstore.Models.Program;

/**
 * Created by ilgar on 03.07.2016.
 */
public class DownloadTaskInfo {

    public DownloadTaskInfo(DownloadTask task, Program program){
        this.downloadTask = task;
        this.program = program;
    }

    private DownloadTask downloadTask;
    private Program program;

    public DownloadTask getDownloadTask() {
        return downloadTask;
    }

    public void setDownloadTask(DownloadTask downloadTask) {
        this.downloadTask = downloadTask;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }
}
