package com.jkiller.killer.basics.thread;

public class MyThread extends Thread {

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            System.out.println(getName() + "打了" + i + "个小兵");
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new MyThread();
        Thread thread2 = new MyThread();
        Thread thread3 = new MyThread();
        thread1.setName("鲁班");
        thread2.setName("刘备");
        thread3.setName("亚瑟");
        // 启动线程 会交替执行
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
