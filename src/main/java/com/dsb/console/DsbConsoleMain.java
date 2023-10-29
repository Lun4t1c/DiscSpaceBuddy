package com.dsb.console;

import com.dsb.core.DsbScanner;

import java.util.Scanner;

public class DsbConsoleMain {
    public static void main(String[] args) {
        DsbScanner dsbScanner = new DsbScanner();
        Scanner scanner = new Scanner(System.in);

        char choice;

        boolean b = true;

        printSplashScreen();

        while (b) {
            printMenu();
            choice = scanner.next().charAt(0);
            switch (choice) {
                case 's':
                    dsbScanner.performScan();
                    break;
                case 'q':
                    b = false;
                    break;
                default:
                    System.err.println("Undefined command, maggot :D...");
                    break;
            }
        }
    }

    public static void printSplashScreen() {
        System.out.println(
                """
                        ==============================
                        "        DiskSpaceBuddy      "
                        ==============================
                        """
        );
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
