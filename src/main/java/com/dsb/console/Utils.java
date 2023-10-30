package com.dsb.console;

public class Utils {
    private static final long BYTES_IN_GB = 1_073_741_824;
    private static final long BYTES_IN_MB = 1_048_576;
    private static final int BYTES_IN_KB = 1_024;

    public static String normalizeBytesSize(long s) {
        long number = 0;
        long bytesSize = s;

        if (bytesSize >= BYTES_IN_GB) {
            while (bytesSize > BYTES_IN_GB) {
                number++;
                bytesSize -= BYTES_IN_GB;
            }

            return number + " GB";
        } else if (bytesSize >= BYTES_IN_MB) {
            while (bytesSize > BYTES_IN_MB) {
                number++;
                bytesSize -= BYTES_IN_MB;
            }

            return number + " MB";
        } else if (bytesSize >= BYTES_IN_KB) {
            while (bytesSize > BYTES_IN_KB) {
                number++;
                bytesSize -= BYTES_IN_KB;
            }

            return number + " KB";
        } else {
            return bytesSize + " B";
        }
    }
}
