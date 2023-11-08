package com.dsb.core.utils.events;

import com.dsb.core.models.DirectoryModel;

public class DirectoryScannedEvent {
    private DirectoryModel directory;

    public DirectoryScannedEvent(DirectoryModel directory) {
        this.directory = directory;
    }

    public DirectoryModel getDirectory() {
        return directory;
    }
}
