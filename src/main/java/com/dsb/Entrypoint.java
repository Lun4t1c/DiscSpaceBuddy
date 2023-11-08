package com.dsb;

import com.dsb.console.DsbConsoleMain;
import com.dsb.core.utils.StartingArgsContext;
import com.dsb.gui.DsbGUIAppMain;

public class Entrypoint {
    public static void main(String[] args) {
        StartingArgsContext startingArgs = parseArgs(args);

        if (startingArgs.isGUI) {
            DsbGUIAppMain.main(startingArgs);
        } else {
            DsbConsoleMain.main(startingArgs);
        }
    }

    private static StartingArgsContext parseArgs(String[] args) {
        StartingArgsContext startingArgs = new StartingArgsContext();
        if (args.length == 0) return  startingArgs;

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "--gui":
                    startingArgs.isGUI = true;
                    break;
            }
        }

        return startingArgs;
    }
}
