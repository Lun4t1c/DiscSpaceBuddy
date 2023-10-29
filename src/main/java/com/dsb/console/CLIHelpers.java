package com.dsb.console;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CLIHelpers {
    public static void printResourceFile(ResourceFilesEnum resourceFile) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(DsbConsoleMain.class.getResource(resourceFile.getResourceName()).toURI()));
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static void printMenu() {
        System.out.println(
                """
                        ==============================
                        "      Press 's' to scan     "
                        "      Press 'q' to exit     "
                        "      Press '?' for help    "
                        ==============================
                        """
        );
    }
}
