package com.dsb.console;

import com.dsb.core.DsbScanner;

public class DsbConsoleMain {
    public static void main(String[] args) {
        printSplashScreen();

        new DsbScanner();
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
}
