package com.dsb.core.models;

import java.nio.file.Path;

public class DirectoryModel {
    private Path path;
    private long size;

    public DirectoryModel(Path path, long size) {
        this.path = path;
        this.size = size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Path getPath() {
        return path;
    }

    public long getSize() {
        return size;
    }
}
