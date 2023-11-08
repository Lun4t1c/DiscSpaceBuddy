package com.dsb.core;

import com.dsb.core.models.DirectoryModel;
import com.dsb.core.models.FileModel;
import com.dsb.core.utils.events.DirectoryScannedEventSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class DsbScanner {
    public List<Path> DiscsList = new ArrayList<>();
    public List<DirectoryModel> DirectoriesList = new ArrayList<>();
    public List<FileModel> FilesList = new ArrayList<>();
    public DirectoryScannedEventSource eventSource = new DirectoryScannedEventSource();

    public DsbScanner() {

    }

    public CompletableFuture<Void> performFullScan() {
        FileSystem filesystem = FileSystems.getDefault();
        List<CompletableFuture<Void>> taskList = new ArrayList<>();

        for (Path disc : filesystem.getRootDirectories()) {
            DiscsList.add(disc);
            taskList.add(CompletableFuture.runAsync(() -> ScanDirectory(disc.toString(), false)));

            for (File file : new File(disc.toUri()).listFiles()) {
                if (file.isDirectory()) {
                    taskList.add(CompletableFuture.runAsync(() -> ScanDirectory(file.getPath(), false)));
                }
            }
        }

        return CompletableFuture.allOf(taskList.toArray(new CompletableFuture[0]));
    }

    private void ScanDirectory(String pathString, boolean isRecursive) {
        List<CompletableFuture<Void>> taskList = new ArrayList<>();
        Path path = Paths.get(pathString);
        DirectoryModel newDirectory = new DirectoryModel(path, 0);

        try (DirectoryStream<Path> subdirectories = java.nio.file.Files.newDirectoryStream(path)) {
            for (Path subdirectory : subdirectories) {
                if (java.nio.file.Files.isRegularFile(subdirectory)) {
                    BasicFileAttributes attributes = Files.readAttributes(subdirectory, BasicFileAttributes.class);
                    FilesList.add(new FileModel(subdirectory, attributes.size()));
                    newDirectory.setSize(newDirectory.getSize() + attributes.size());
                } else if (java.nio.file.Files.isDirectory(subdirectory)) {
                    if (isRecursive) {
                        taskList.add(CompletableFuture.runAsync(() -> ScanDirectory(subdirectory.toString(), true)));
                    }
                }
            }

            DirectoriesList.add(newDirectory);
            eventSource.fireEvent(newDirectory);

            CompletableFuture<Void> allOf = CompletableFuture.allOf(taskList.toArray(new CompletableFuture[0]));
            try {
                allOf.get();
            } catch (InterruptedException | ExecutionException e) {
                System.err.println("Scanning was interrupted, xDddd...");
                e.printStackTrace();
            }
        } catch (AccessDeniedException e) {
            System.err.println("Access denied: " + pathString);
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("DsbScanner have thrown ExecutionException, xDddd...");
            e.printStackTrace();
        }
    }
}
