package test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * TestTime
 *
 * @author by gatesma.
 */
public class TestTime {

    private static final DateTimeFormatter MOUTH_FORMATTER = DateTimeFormatter.ofPattern("yyyyMM");


    /**
     * 返回当前月份 yyyyMM
     *
     * @return yyyyMM
     */
    public static Long getCurrentMouth() {
        return Long.valueOf(MOUTH_FORMATTER.format(LocalDateTime.now()));
    }

    public static void main(String[] args) {
        System.out.printf(getCurrentMouth() + "");
    }

}
