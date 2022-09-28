package com.jkiller.killer.basics.thread;

import java.util.concurrent.Callable;

public class ReturnThreadNameCallable implements Callable<String> {

    public String call() throws Exception{
        Thread.sleep(1000);
        return Thread.currentThread().getName();
    }
}
