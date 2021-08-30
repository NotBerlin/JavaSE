package com.ThreadPCModal;


// 测试生产者/消费者模型，利用缓冲区
public class ThreadPCModal {
    public static void main(String[] args) {
        SynContainer container = new SynContainer();
        new Productor(container).start();
        new Customer(container).start();
    }
}

class Productor extends Thread {
    SynContainer container;

    public Productor(SynContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                container.push(new Chicken(i));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("生产了" + i + "只鸡");
        }
    }
}

class Customer extends Thread {
    SynContainer container;

    public Customer(SynContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                System.out.println("消费者拿走" + container.pop().id + "只鸡");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Chicken {
    int id;
    public Chicken(int id) {
        this.id = id;
    }
}

class SynContainer {
    Chicken[] chickens = new Chicken[10];
    int count = 0;

    public synchronized void push(Chicken chicken) throws InterruptedException {
        // 如果容器满， 等待消费者消费
        if (count == chickens.length) {
            this.wait();
        }
        // 如果容器没有满， 将产品丢入容器
        chickens[count] = chicken;
        count++;

        // 通知消费者
        this.notifyAll();
    }

    public synchronized Chicken pop() throws InterruptedException {
        // 判断容器的鸡，等待生产
        if (count == 0) {
            this.wait();
        }
        count--;
        Chicken chicken = chickens[count];

        this.notifyAll();
        return chicken;
    }
}