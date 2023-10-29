package com.dsb.core;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class DsbScanner {
    public List<Path> Discs = new LinkedList<>();
    public List<Path> Directories = new ArrayList<>();
    List<CompletableFuture<Void>> taskList = new ArrayList<>();

    public DsbScanner() {
        performScan();
    }

    public void performScan() {
        FileSystem filesystem = FileSystems.getDefault();

        for (Path disc : filesystem.getRootDirectories()) {
            System.out.println("Found disc: " + disc + " - scanning...");
            Discs.add(disc);
            ScanDirectory(disc.toString());
        }

        System.out.println("Scanning done.");
    }

    private void ScanDirectory(String path) {
        System.out.println("Scanning directory " + path);

        try (DirectoryStream<Path> subfolders = java.nio.file.Files.newDirectoryStream(Paths.get(path))) {
            for (Path subfolder : subfolders) {
                if (java.nio.file.Files.isDirectory(subfolder)) {
                    System.out.println("Subfolder: " + subfolder);
                    Directories.add(subfolder);
                    //ScanDirectory(subfolder.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
