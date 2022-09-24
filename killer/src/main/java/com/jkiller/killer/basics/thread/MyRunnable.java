package com.jkiller.killer.basics.thread;

public class MyRunnable implements Runnable {

    public void run() {
        for (int i = 1; i <= 100; i++) {
            // sleep时异常要显式处理
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "打了" + i + "个小兵");
        }
    }

    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread1 = new Thread(myRunnable, "张飞");
        Thread thread2 = new Thread(myRunnable, "吕布");
        Thread thread3 = new Thread(myRunnable, "貂蝉");
        // 交替执行
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
