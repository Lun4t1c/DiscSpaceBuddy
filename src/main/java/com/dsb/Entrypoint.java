package com.dsb;

import com.dsb.console.DsbConsoleMain;
import com.dsb.gui.DsbGUIAppMain;

public class Entrypoint {
    public static void main(String[] args) {
        if (args.length == 0) {
            DsbGUIAppMain.main(args);
        } else {
            DsbConsoleMain.main(args);
        }
    }
}
