package com.CountDown;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CountDown {
    public static void main(String[] args) throws InterruptedException {
//        tenDown();

        // 当前时间
        Date startTime = new Date(System.currentTimeMillis());

        while (true) {
            Thread.sleep(1000);
            System.out.println(new SimpleDateFormat("HH:mm:ss").format(startTime));
            // 更新当前时间
            startTime = new Date(System.currentTimeMillis());
        }
    }

    public static void tenDown() throws InterruptedException {
        int num = 10;
        while (true) {
            Thread.sleep(1000);
            System.out.println("num：" + num--);
            if (num <= 0) {
                break;
            }
        }
    }
}
