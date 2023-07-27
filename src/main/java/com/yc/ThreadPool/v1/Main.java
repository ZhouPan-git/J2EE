package com.yc.ThreadPool.v1;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author zp
 * @Date 2023/7/25 18:48
 * @PackageName:com.yc.ThreadPool.v1
 * @ClassName: Test
 * @Description:
 * @Version 1.0
 */
public class Main {

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            CurrentTimeThread thread = new CurrentTimeThread();
            thread.start();
        }
    }

}
class CurrentTimeThread extends Thread {

    @Override
    public void run() {
        System.out.println(new Date());
    }

}