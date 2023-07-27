package com.yc.ThreadPool.v2;

import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author zp
 * @Date 2023/7/25 19:12
 * @PackageName:com.yc.ThreadPool.v2
 * @ClassName: Main
 * @Description:
 * @Version 1.0
 */
public class Main {

    public static void main(String[] args) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(5, 5, 0L,
                                                          TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        for (int i = 0; i < 5; i++) {
            pool.execute(new CurrentTimeTask());
        }
    }

}

class CurrentTimeTask implements Runnable {

    @Override
    public void run() {
        System.out.println(new Date());
    }

}