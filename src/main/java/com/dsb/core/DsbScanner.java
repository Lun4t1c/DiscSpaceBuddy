package com.dsb.core;

import java.io.IOException;
import java.nio.file.*;
import java.util.LinkedList;
import java.util.List;

public class DsbScanner {
    public List<Path> Directories = new LinkedList<>();

    public DsbScanner() {
        performScan();
    }

    public void performScan() {
        FileSystem filesystem = FileSystems.getDefault();

        for (Path disc : filesystem.getRootDirectories()) {
            System.out.println("Found disc: " + disc + " - scanning...");
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
