package com.dsb.core;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class DsbScanner {
    List<CompletableFuture<Void>> taskList = new ArrayList<>();
    public List<Path> Discs = new ArrayList<>();
    public List<Path> Directories = new ArrayList<>();

    public DsbScanner() {

    }

    public CompletableFuture<Void> performScan() {
        FileSystem filesystem = FileSystems.getDefault();
        List<CompletableFuture<Void>> taskList = new ArrayList<>();

        for (Path disc : filesystem.getRootDirectories()) {
            Discs.add(disc);
            taskList.add(CompletableFuture.runAsync(() -> ScanDirectory(disc.toString())));
        }

        return CompletableFuture.allOf(taskList.toArray(new CompletableFuture[0]));
    }

    private void ScanDirectory(String path) {
        System.out.println("Scanning directory " + path);
        //List<CompletableFuture<Void>> taskList = new ArrayList<>();

        try (DirectoryStream<Path> subfolders = java.nio.file.Files.newDirectoryStream(Paths.get(path))) {
            for (Path subfolder : subfolders) {
                if (java.nio.file.Files.isDirectory(subfolder)) {
                    System.out.println("Subfolder: " + subfolder);
                    Directories.add(subfolder);
                    /*
                    taskList.add(CompletableFuture.runAsync(() -> {
                        ScanDirectory(subfolder.toString());
                    }));
                     */
                }
            }

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
