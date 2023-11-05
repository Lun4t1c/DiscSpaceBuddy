package com.dsb.console;

public class Utils {
    private static final long BYTES_IN_GB = 1_073_741_824;
    private static final long BYTES_IN_MB = 1_048_576;
    private static final int BYTES_IN_KB = 1_024;

    public static String normalizeBytesSize(long s) {
        double bytesSize = (double) s;
        String[] units = {"B", "KB", "MB", "GB"};

        int unitIndex = 0;

        while (bytesSize >= 1024.0 && unitIndex < units.length - 1) {
            bytesSize /= 1024.0;
            unitIndex++;
        }

        return String.format("%.2f %s", bytesSize, units[unitIndex]);
    }
}
