package com.dsb.console;

public class DsbConsoleMain {
    public static void main(String[] args) {
        printSplashScreen();
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
