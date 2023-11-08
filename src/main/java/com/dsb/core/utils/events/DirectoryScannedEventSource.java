package com.dsb.core.utils.events;

import com.dsb.core.models.DirectoryModel;

import java.util.ArrayList;
import java.util.List;

public class DirectoryScannedEventSource {
    private List<IDirectoryScannedListener> listeners = new ArrayList<>();

    public void addEventListener(IDirectoryScannedListener listener) {
        listeners.add(listener);
    }

    public void removeEventListener(IDirectoryScannedListener listener) {
        listeners.remove(listener);
    }

    public void fireEvent(DirectoryModel directory) {
        DirectoryScannedEvent event = new DirectoryScannedEvent(directory);
        for (IDirectoryScannedListener listener : listeners) {
            listener.onDirectoryScanned(event);
        }
    }
}
