package com.dsb.discspacebuddy;

import com.dsb.discspacebuddy.gui.DsbGUIAppMain;

public class Entrypoint {
    public static void main(String[] args) {
        if (true) {
            DsbGUIAppMain.main(new String[0]);
        } else {
            System.out.println("Starting non-GUI...");
        }
    }
}
