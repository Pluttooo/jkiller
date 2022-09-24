package com.jkiller.killer.basics.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MyCallable implements Callable<Integer> {

    public Integer call() {
        return 123;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallable myCallable = new MyCallable();
        FutureTask<Integer> callableFutureTask = new FutureTask<>(myCallable);
        Thread callableThread = new Thread(callableFutureTask);
        callableThread.start();
        System.out.println(callableFutureTask.get());
    }
}
