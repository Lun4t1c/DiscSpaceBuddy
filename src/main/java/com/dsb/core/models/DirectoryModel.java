package com.dsb.core.models;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DirectoryModel {
    private final Path path;
    private long size;

    private List<DirectoryModel> subDirectories = new ArrayList<>();
    private List<FileModel> files = new ArrayList<>();

    public DirectoryModel(Path path, long size) {
        this.path = path;
        this.size = size;
    }

    public Path getPath() {
        return path;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void addFile(FileModel file) {
        files.add(file);
    }

    public void addSubDirectory(DirectoryModel directory) {
        subDirectories.add(directory);
    }
}
