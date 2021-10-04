package com.bassoli.utils;

import java.util.concurrent.TimeUnit;

public class TimeFormatter {

    private long time;

    public TimeFormatter(long time) {
        this.time = time;
    }

    public String format() {
        if (time == 0) {
            return "0 segundos";
        }

        long days = TimeUnit.MILLISECONDS.toDays(time);
        long hours = TimeUnit.MILLISECONDS.toHours(time) - (days * 24);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(time) - (TimeUnit.MILLISECONDS.toHours(time) * 60);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(time) - (TimeUnit.MILLISECONDS.toMinutes(time) * 60);
        StringBuilder sb = new StringBuilder();

        if (days > 0)
            sb.append(days + (days == 1 ? " dia" : " dias"));

        if (hours > 0)
            sb.append(hours > 0 ? (hours > 0 ? ", " : " e ") : "").append(hours + (hours == 1 ? " hora" : " horas"));

        if (minutes > 0)
            sb.append(days > 0 || hours > 0 ? (seconds > 0 ? ", " : " e ") : "").append(minutes + (minutes == 1 ? " minuto" : " minutos"));

        if (seconds > 0)
            sb.append(days > 0 || hours > 0 || minutes > 0 ? " e " : (sb.length() > 0 ? ", " : "")).append(seconds + (seconds == 1 ? " segundo" : " segundos"));

        String s = sb.toString();
        return s.isEmpty() ? "0 segundos" : s;
    }

    public static String format(long time) {
        return new TimeFormatter(time).format();
    }

}
