package com.dsb.core;

import com.dsb.core.models.DirectoryModel;
import com.dsb.core.models.FileModel;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class DsbScanner {
    public List<Path> DiscsList = new ArrayList<>();
    public List<DirectoryModel> DirectoriesList = new ArrayList<>();
    public List<FileModel> FilesList = new ArrayList<>();

    public DsbScanner() {

    }

    public CompletableFuture<Void> performFullScan() {
        FileSystem filesystem = FileSystems.getDefault();
        List<CompletableFuture<Void>> taskList = new ArrayList<>();

        for (Path disc : filesystem.getRootDirectories()) {
            DiscsList.add(disc);
            taskList.add(CompletableFuture.runAsync(() -> ScanDirectory(disc.toString(), false)));
        }

        return CompletableFuture.allOf(taskList.toArray(new CompletableFuture[0]));
    }

    private void ScanDirectory(String pathString, boolean isRecursive) {
        List<CompletableFuture<Void>> taskList = new ArrayList<>();
        Path path = Paths.get(pathString);
        DirectoryModel newDirectory = new DirectoryModel(path, 0);

        try (DirectoryStream<Path> subfolders = java.nio.file.Files.newDirectoryStream(path)) {
            for (Path subfolder : subfolders) {
                if (java.nio.file.Files.isRegularFile(subfolder)) {
                    BasicFileAttributes attributes = Files.readAttributes(subfolder, BasicFileAttributes.class);
                    FilesList.add(new FileModel(subfolder, attributes.size()));
                    newDirectory.setSize(newDirectory.getSize() + attributes.size());
                }
                else if (java.nio.file.Files.isDirectory(subfolder)) {
                    if (isRecursive) {
                        taskList.add(CompletableFuture.runAsync(() -> ScanDirectory(subfolder.toString(), true)));
                    }
                }
            }

            DirectoriesList.add(newDirectory);
            CompletableFuture<Void> allOf = CompletableFuture.allOf(taskList.toArray(new CompletableFuture[0]));
            try {
                allOf.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
