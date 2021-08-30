package com.ThreadPCModal;


// 信号灯法，标识位
public class ThreadPCModalSignalLamp {
    public static void main(String[] args) {
        TV tv = new TV();
        new Watcher(tv);
        new Player(tv);
    }
}

// 演员
class Player extends Thread {
    TV tv;

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                try {
                    tv.play("快乐大本营");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    tv.play("抖音记录生活");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Player(TV tv) {
        this.tv = tv;
    }

}

// 观众
class Watcher extends Thread {
    TV tv;
    public Watcher(TV tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        try {
            tv.watch();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// 节目
class TV {
    // 演员表演，观众等待 T
    // 观众观看，演员等待 F
    String voice;
    boolean flag = true;

    public synchronized void play(String voice) throws InterruptedException {
        if (!flag) {
            this.wait();
        }
        System.out.println("演员表演了" + voice);
        // 通知
        this.notifyAll();
        this.voice = voice;
        this.flag = !this.flag;
    }
    public synchronized void watch() throws InterruptedException {
        if (flag) {
            this.wait();
        }
        System.out.println("观众观看了" + voice);
        this.notifyAll();
        this.flag = !this.flag;
    }
}