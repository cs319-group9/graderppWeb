package com.cs319.graderpp.models;

import java.io.InputStream;

/**
 * Created by burak on 25.11.2015.
 */
public class MakeFile extends File {
    public MakeFile(String fileName, String fullPath, InputStream inputStream, long size) {
        super(fileName, fullPath, inputStream, size);
    }
}
