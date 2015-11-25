package com.cs319.graderpp.models;

import java.io.InputStream;

/**
 * Created by burak on 25.11.2015.
 */
public abstract class File {
    private String fileName;
    private String fullPath;
    private InputStream inputStream;
    private long size;

    public File(String fileName, String fullPath, InputStream inputStream, long size) {
        this.fileName = fileName;
        this.fullPath = fullPath;
        this.inputStream = inputStream;
        this.size = size;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
