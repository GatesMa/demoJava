package test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * test.TestDate
 *
 * @author by gatesma on 2022/3/8.
 */
public class TestDate {

    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        System.out.println(date.minusMonths(1));
        LocalDate localDate = date.minusDays(1);
        System.out.println(localDate.plusDays(1).isBefore(LocalDate.now()));
        System.out.println(localDate.isBefore(localDate));

//        System.out.println(Integer.parseInt(date.format(DateTimeFormatter.BASIC_ISO_DATE)));
    }

}
