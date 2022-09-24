package com.jkiller.killer.basics.thread;

public class MyRunnableWithSetDaemon implements Runnable{

    public void run() {
        for (int i = 1; i <=  100; i++) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "打了" + i + "个小兵");
        }
    }

    public static void main(String[] args) {
        MyRunnableWithSetDaemon myRunnableWithSetDaemon = new MyRunnableWithSetDaemon();
        Thread thread1 = new Thread(myRunnableWithSetDaemon, "张飞");
        Thread thread2 = new Thread(myRunnableWithSetDaemon, "吕布");
        Thread thread3 = new Thread(myRunnableWithSetDaemon, "貂蝉");
        thread1.setDaemon(true);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
