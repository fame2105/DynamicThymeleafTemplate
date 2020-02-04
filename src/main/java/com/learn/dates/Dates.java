package com.learn.dates;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Dates {
    public static void main(String[] args) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd-M-yyyy");
        LocalDate today = LocalDate.parse("12-9-2019", pattern);
        dateFromatter("12-9-2019");
    }

    public static void dateFromatter(String date){
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd-M-yyyy");
        LocalDate today = LocalDate.parse(date, pattern);
        System.out.println(LocalDate.parse( "2019-10-11", DateTimeFormatter.ofPattern( "yyyy-M-dd")).toString());
//        System.out.println(today.toString());
//        String formattedDate = today.format(pattern);
//        System.out.println(date.format(pattern));
    }
}
