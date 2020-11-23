package com.waiwaiwai.thread.simp.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {

    public static SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");

    public DateFormatUtil() {

    }

    public static String dateFormat(Date date) {
        return format.format(date);
    }
}
