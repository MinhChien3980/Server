package org.example.server_mobile.util;

public class StatusUtils {
    public static Byte statusToByte(String status) {
        if ("ACTIVE".equalsIgnoreCase(status)) {
            return 1;
        } else if ("INACTIVE".equalsIgnoreCase(status)) {
            return 0;
        }
        return 0;
    }
}
