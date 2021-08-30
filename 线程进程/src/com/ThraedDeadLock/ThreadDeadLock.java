package com.ThraedDeadLock;


// 死锁：多个线程互相抱着对方的资源，僵持不下，最后程序崩溃
// 产生死锁的四个必要条件
// 互斥条件：一个资源一次只能被一个进程使用
// 请求与保持条件：一个进程因请求资源阻塞时，对已获得的资源不放
// 不剥夺条件：进程已获得的资源，在未使用完之前，不能强行剥夺
// 循环等待条件：若干进程之间形成首尾相连的循环等待资源关系
public class ThreadDeadLock {
    public static void main(String[] args) throws InterruptedException {
        MakeUp girl1 = new MakeUp(0,"灰姑娘");
        MakeUp girl2 = new MakeUp(1,"蓝姑娘");
        girl1.start();
        girl2.start();
    }
}

class Lipstick {

}

class Mirror {

}

class MakeUp extends Thread {

    static Lipstick lipstick = new Lipstick();
    static Mirror mirror = new Mirror();

    int choose;
    String girlName;

    public MakeUp(int choose, String girlName) throws InterruptedException {
        this.choose = choose;
        this.girlName = girlName;
    }

    private void makeup() throws InterruptedException {
        if (choose == 0) {
            synchronized (lipstick) {
                System.out.println(girlName + "获得口红的锁");
                Thread.sleep(1000);
                // 一秒钟后想获得镜子
                synchronized (mirror) {
                    System.out.println(girlName + "获得镜子的锁");
                }
            }
        } else {
            synchronized (mirror) {
                System.out.println(girlName + "获得镜子的锁");
                Thread.sleep(2000);
                // 一秒钟后想获得镜子
                synchronized (lipstick) {
                    System.out.println(girlName + "获得口红的锁");
                }
            }
        }
    }

    @Override
    public void run() {
        try {
            makeup();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}