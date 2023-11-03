package com.dsb.console;

public enum ResourceFilesEnum {
    SPLASH("splash.txt"),
    HELP("help.txt");

    private final String resourceName;

    ResourceFilesEnum(String s) {
        this.resourceName = s;
    }

    public String getResourceName() {
        return resourceName;
    }
}
