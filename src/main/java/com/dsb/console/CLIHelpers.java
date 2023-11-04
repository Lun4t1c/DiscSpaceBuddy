package com.dsb.console;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class CLIHelpers {
    public static void printResourceFile(ResourceFilesEnum resourceFile) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(Objects.requireNonNull(DsbConsoleMain.class.getResource(resourceFile.getResourceName())).toURI()));
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("CLI Helpers have thrown IOException, xDddd...");
        }
        catch (URISyntaxException e){
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
