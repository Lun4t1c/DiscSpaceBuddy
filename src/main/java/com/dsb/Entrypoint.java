package com.dsb;

import com.dsb.console.DsbConsoleMain;
import com.dsb.core.utils.StartingArgsContext;
import com.dsb.gui.DsbGUIAppMain;

public class Entrypoint {
    public static void main(String[] args) {
        StartingArgsContext startingArgs = parseArgs(args);

        if (startingArgs.isGUI) {
            DsbGUIAppMain.mainLoop(startingArgs);
        } else {
            DsbConsoleMain.mainLoop(startingArgs);
        }
    }

    private static StartingArgsContext parseArgs(String[] args) {
        StartingArgsContext startingArgs = new StartingArgsContext();

        for (String arg : args) {
            switch (arg) {
                case "--gui":
                    startingArgs.isGUI = true;
                    break;
                case "--no-gui":
                    startingArgs.isGUI = false;
                    break;
                default:
                    break;
            }
        }

        return startingArgs;
    }
}
