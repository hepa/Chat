package hu.unideb.inf.util;


public class InetValidatorUtil {

    public static boolean checkIP(String ip) {

        if (ip.isEmpty()) {
            return false;
        }

        String[] parts = ip.split("\\.");
        int n = 0;
        for (String s : parts) {
            n++;
            int i;
            try {
                i = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                return false;
            }

            if (i < 0 || i > 255) {
                return false;
            }
        }
        if (n != 4) {
            return false;
        }
        return true;
    }

    public static boolean checkPort(String port) {

        if (port.isEmpty()) {
            return false;
        }

        int p;
        try {
            p = Integer.parseInt(port);
        } catch (NumberFormatException e) {
            return false;
        }

        if (p < 0 || p > 66535) {
            return false;
        }
        return true;
    }

    public static int checkName(String name) {
        if (name.isEmpty()) {
            return 1;
        } else if (name.length() < 3) {
            return 2;
        }
        return 0;
    }
}
