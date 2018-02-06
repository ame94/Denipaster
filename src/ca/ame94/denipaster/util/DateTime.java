package ca.ame94.denipaster.util;

import java.util.Date;

public class DateTime {

    public static String getDateFromUnixtime(long unixtime) {
        return new Date(unixtime).toString();
    }
}
