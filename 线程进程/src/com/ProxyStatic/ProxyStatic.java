package com.ProxyStatic;


// 静态代理模式
// 真实对象和代理对象都要实现同一个接口
// 代理对象要代理真实角色

// 好处
// 代理对象可以做很多真实对象做不了的事情
// 真实对象专注做自己的事情
public class ProxyStatic {
    public static void main(String[] args) {
//        new Thread(() -> System.out.println("我爱你")).start();
//        线程底层原理就是运用到了静态代理
//        Thread类实现了Runnable接口
//        () -> {} Lambda表达式
        new WeddingCompany(new You()).HappyMarry();
    }
}

interface Marry {
    void HappyMarry();
}

// 真实角色
class You implements Marry{

    @Override
    public void HappyMarry() {
        System.out.println("爸爸我要结婚了");
    }
}

// 帮助结婚
class WeddingCompany implements Marry{
    private Marry target;

    public WeddingCompany(Marry marry) {
        this.target = marry;
    }

    @Override
    public void HappyMarry() {
        before();
        // 这就是真实对象
        this.target.HappyMarry();
        after();
    }

    private void after() {
        System.out.println("结婚之后");
    }

    private void before() {
        System.out.println("结婚之前");
    }
}