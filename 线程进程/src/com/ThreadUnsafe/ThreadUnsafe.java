package com.ThreadUnsafe;

import java.util.ArrayList;
import java.util.List;

// 线程不安全，有负数
// 使用sleep发大线程的不安全
public class ThreadUnsafe implements Runnable{
    private int ticketNumbers = 10;
    private boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        // 买票案例，不安全
//        ThreadUnsafe threadUnsafe = new ThreadUnsafe();
//
//        new Thread(threadUnsafe, "老王").start();
//        new Thread(threadUnsafe, "老陈").start();
//        new Thread(threadUnsafe, "老孙").start();

        // 银行不安全
//        Account account = new Account(1000000, "结婚基金");
//        Bank me = new Bank(account, 503000, 32100);
//        Bank girl = new Bank(account, 509300, 32100);
//        me.start();
//        girl.start();

        // 线程不安全的集合
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                list.add(Thread.currentThread().getName());
            }).start();
        }
        Thread.sleep(2000);
        System.out.println(list.size());
    }

    @Override
    public void run() {
        while (flag) {
            try {
                buy();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void buy() throws InterruptedException {
        if (ticketNumbers <= 0) {
            flag = false;
            return;
        }
        Thread.sleep(100);
        System.out.println(Thread.currentThread().getName() + "买到票了" + ticketNumbers--);
    }
}

// 账户
class Account{
    int money;
    String name;

    public Account(int money, String name) {
        this.money = money;
        this.name = name;
    }
}

// 银行，取钱操作
class Bank extends Thread {
    Account account;
    // 取多少钱
    int drawMoney;
    // 还剩多少钱
    int nowMoney;

    public Bank(Account account,int drawMoney,int nowMoney) {
        this.account = account;
        this.drawMoney = drawMoney;
        this.nowMoney = nowMoney;
    }

    @Override
    public void run() {
        if (account.money - drawMoney < 0) {
            System.out.println(Thread.currentThread().getName() + "钱不够，取不了");
            return;
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        account.money = account.money - drawMoney;
        nowMoney = nowMoney + drawMoney;
        System.out.println(account.name + "账户还有" + account.money);
        System.out.println(Thread.currentThread().getName() + "手里还有" + nowMoney);
    }
}