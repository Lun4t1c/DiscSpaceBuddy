package com.dsb.core.models;

import java.nio.file.Path;

public class FileModel {
    private final Path path;
    private final long size;

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

    // TODO Implement
    public void openInExplorer() {
        throw new UnsupportedOperationException();
    }

    // TODO Implement
    public void delete() {
        throw new UnsupportedOperationException();
    }
}
