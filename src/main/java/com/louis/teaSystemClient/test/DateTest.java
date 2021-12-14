package com.louis.teaSystemClient.test;

import java.text.SimpleDateFormat;

/**
 * 赖小燚
 * www.louis.com
 */
public class DateTest {
    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String current = simpleDateFormat.format(System.currentTimeMillis());
        System.out.println(current);
    }
}
