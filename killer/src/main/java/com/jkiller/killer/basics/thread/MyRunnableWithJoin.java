package com.jkiller.killer.basics.thread;

public class MyRunnableWithJoin implements Runnable{

    public void run() {
        for (int i = 1; i <= 100; i++) {
            // sleep时异常要显式处理
            try {
                Thread.sleep(200);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "打了" + i + "个小兵");
        }
    }

    public static void main(String[] args) {
        MyRunnableWithJoin myRunnableWithJoin = new MyRunnableWithJoin();
        Thread thread1 = new Thread(myRunnableWithJoin, "张飞");
        Thread thread2 = new Thread(myRunnableWithJoin, "吕布");
        Thread thread3 = new Thread(myRunnableWithJoin, "貂蝉");
        thread1.start();
        try {
            // thread1 执行完毕才会执行 thread2、thread3
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
        thread3.start();
    }
}
