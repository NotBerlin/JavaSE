package com.Lambda;

public class Lambda {
    public static void main(String[] args) {
        ILike like = new Like();
        like.lambda();

        like = new Like2();
        like.lambda();

        // 局部内部类
        class Like3 implements ILike {

            @Override
            public void lambda() {
                System.out.println("I Like Lambda3");
            }
        }

        like = new Like3();
        like.lambda();

        // 匿名内部类，没有类的名称，必须借助接口或者父类
        like = new ILike() {
            @Override
            public void lambda() {
                System.out.println("I Like Lambda4");
            }
        };
        like.lambda();

        // 用Lambda表达式
        like = () -> {
                System.out.println("I Like Lambda5");
        };
        like.lambda();
    }

    static class Like2 implements ILike {

        @Override
        public void lambda() {
            System.out.println("I Like Lambda2");
        }
    }
}

interface ILike {
    void lambda();
}

class Like implements ILike {

    @Override
    public void lambda() {
        System.out.println("I Like Lambda");
    }
}