package com.Callable;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;

public class ImplementsCallable implements Callable<Boolean> {
    @Override
    public Boolean call() throws Exception {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.downloader(url, name);
        System.out.println("下载图片名为" + name);
        return true;
    }

    private String url;
    private String name;

    public ImplementsCallable(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ImplementsCallable extendsThread = new ImplementsCallable("https://photo7n.gracg.com/332471_1_9a58cc928b26d495108e713e3e20ef78.jpg?imageMogr2/auto-orient/thumbnail/1200x/blur/1x0/quality/98", "七夕.jpg");
        ImplementsCallable extendsThread2 = new ImplementsCallable("https://photo7n.gracg.com/406808_m_ebb6f27b759f9c086f59f5040dbfe15a.jpeg?imageMogr2/auto-orient/thumbnail/1200x/blur/1x0/quality/98", "梦幻新诛仙手游.jpg");

        ExecutorService ser = Executors.newFixedThreadPool(2);
//        java代码是同步的，想要异步操作只能添加线程
        ser.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("start---");
                    // shutdownNow 杀死线程池会报错
                    Thread.sleep(10000);
                    System.out.println("end----");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Future<Boolean> result1 = ser.submit(extendsThread);
        Future<Boolean> result2 = ser.submit(extendsThread2);
        boolean r1 = result1.get();
        boolean r2 = result2.get();
        System.out.println(r1);
        System.out.println(r2);
        ser.shutdownNow();
    }
}

class WebDownloader {
    public void downloader(String url, String name) {
        try {
            FileUtils.copyURLToFile(new URL(url), new File(name));
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("IO操作异常，WebDownloader方法");
        }
    }
}