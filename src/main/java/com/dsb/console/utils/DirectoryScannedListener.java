package com.dsb.console.utils;

import com.dsb.core.utils.events.DirectoryScannedEvent;
import com.dsb.core.utils.events.IDirectoryScannedListener;

public class DirectoryScannedListener implements IDirectoryScannedListener {
    @Override
    public void onDirectoryScanned(DirectoryScannedEvent event) {
        System.out.println("Received event: " + event.getDirectory().getPath());
    }
}
