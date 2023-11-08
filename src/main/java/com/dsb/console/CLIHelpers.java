package com.dsb.console;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class CLIHelpers {
    public static void printResourceFile(ResourceFilesEnum resourceFile) {
        try {
            String resourceName = resourceFile.getResourceName();
            URL url = DsbConsoleMain.class.getResource(resourceName);
            URI uri = Objects.requireNonNull(url).toURI();
            Path path = Paths.get(uri);
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("CLI Helpers have thrown IOException, xDddd...");
        } catch (URISyntaxException e) {
            System.err.println("CLI Helpers have thrown URISyntaxException, xDddd...");
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
