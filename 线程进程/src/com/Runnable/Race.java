package com.Runnable;

public class Race implements Runnable{

    private static String winer;

    private boolean gameOver(int step) {
        if (winer != null) {
            return true;
        } {
            if  (step >= 100) {
                winer = Thread.currentThread().getName();
                System.out.println("winer:" + winer);
                return true;
            }
        }
        return false;
    }

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if (Thread.currentThread().getName().equals("兔子") && i % 10 == 0) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            boolean flag = gameOver(i);
            if (flag) {
                break;
            }

            System.out.println(Thread.currentThread().getName() + "跑了" + i + "步");
        }
    }

    public static void main(String[] args) {
        Race race = new Race();
        new Thread(race, "兔子").start();
        new Thread(race, "乌龟").start();
    }
}
