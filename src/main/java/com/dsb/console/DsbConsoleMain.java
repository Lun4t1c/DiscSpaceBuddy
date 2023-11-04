package com.dsb.console;

import com.dsb.core.DsbScanner;
import com.dsb.core.StartingArgsContext;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class DsbConsoleMain {
    static DsbScanner dsbScanner;
    static Scanner scanner;

    public static void mainLoop(StartingArgsContext startingArgs) {
        dsbScanner = new DsbScanner();
        scanner = new Scanner(System.in);

        char choice;

        boolean b = true;

        CLIHelpers.printResourceFile(ResourceFilesEnum.SPLASH);

        while (b) {
            CLIHelpers.printMenu();
            choice = scanner.next().charAt(0);
            switch (choice) {
                case 's':
                    performFullScan();
                    break;
                case 'q':
                    b = false;
                    break;
                case '?':
                    CLIHelpers.printResourceFile(ResourceFilesEnum.HELP);
                    break;
                default:
                    System.err.println("Undefined command, maggot :D...");
                    break;
            }
        }
    }

    private static void performFullScan() {
        try {
            dsbScanner.performFullScan().get();
            System.out.printf("Done scanning (found %d files in %d folders)%n", dsbScanner.FilesList.size(), dsbScanner.DirectoriesList.size());
            System.out.print("Press ENTER to continue...");
            int x = System.in.read();
        } catch (InterruptedException e) {
            System.err.println("Scan interrupted: ");
        }
        catch (ExecutionException e) {
            System.err.println("ERROR: ");
            System.err.println("DsbConsoleMain have thrown ExecutionException, xDddd...");
        } catch (IOException e) {
            System.err.println("DsbConsoleMain have thrown IOException, xDddd...");
        }
    }
}
