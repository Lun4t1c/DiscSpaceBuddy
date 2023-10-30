package com.dsb.console;

import com.dsb.core.DsbScanner;
import com.dsb.core.models.DirectoryModel;
import com.dsb.core.models.FileModel;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class DsbConsoleMain {
    static DsbScanner dsbScanner;
    static Scanner scanner;

    public static void main(String[] args) {
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
            System.in.read();
        } catch (InterruptedException exc) {
            System.err.println("Scan interrupted: ");
            exc.printStackTrace();
        }
        catch (ExecutionException exc) {
            System.err.println("ERROR: ");
            exc.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
