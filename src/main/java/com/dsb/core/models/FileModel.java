package com.dsb.core.models;

import java.nio.file.Path;

public class FileModel {
    private Path path;
    private long size;

    public FileModel(Path path, long size) {
        this.path = path;
        this.size = size;
    }

    public Path getPath() {
        return path;
    }

    public long getSize() {
        return size;
    }
}
