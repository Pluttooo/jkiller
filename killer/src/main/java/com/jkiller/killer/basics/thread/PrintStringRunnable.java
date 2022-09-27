package com.jkiller.killer.basics.thread;

import java.util.Date;

public class PrintStringRunnable implements Runnable {

    private String command;

    public PrintStringRunnable(String command) {
        this.command = command;
    }

    public void processCommand() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + "Start. time = " + new Date());
        processCommand();
        System.out.println(Thread.currentThread().getName() + "End. time = " + new Date());
    }

    public String toString() {
        return this.command;
    }
}
