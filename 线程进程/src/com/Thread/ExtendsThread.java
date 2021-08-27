package com.Thread;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

// 继承Thread类
public class ExtendsThread extends Thread {

    private String url;
    private String name;

    public ExtendsThread(String url, String name) {
        this.url = url;
        this.name = name;
    }

    // 线程执行体
    @Override
    public void run() {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.downloader(url, name);
        System.out.println("下载图片名为" + name);
    }

    public static void main(String[] args) {
        ExtendsThread extendsThread = new ExtendsThread("https://photo7n.gracg.com/332471_1_9a58cc928b26d495108e713e3e20ef78.jpg?imageMogr2/auto-orient/thumbnail/1200x/blur/1x0/quality/98", "七夕.jpg");
        ExtendsThread extendsThread2 = new ExtendsThread("https://photo7n.gracg.com/406808_m_ebb6f27b759f9c086f59f5040dbfe15a.jpeg?imageMogr2/auto-orient/thumbnail/1200x/blur/1x0/quality/98", "梦幻新诛仙手游.jpg");
        extendsThread.start();
        extendsThread2.start();
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
