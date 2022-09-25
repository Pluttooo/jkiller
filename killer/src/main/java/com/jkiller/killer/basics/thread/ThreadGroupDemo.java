package com.jkiller.killer.basics.thread;

public class ThreadGroupDemo {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("thread当前所在线程组的名字: " + Thread.currentThread().getThreadGroup().getName());
            System.out.println("thread当前所在线程的名字: " + Thread.currentThread().getName());
        });
        thread.start();
        Thread thread1 = new Thread();
        System.out.println("我是默认线程优先级: " + thread1.getPriority());
        Thread thread2 = new Thread();
        thread2.setPriority(10);
        System.out.println("我是设置后的线程优先级: " + thread2.getPriority());
    }
}
