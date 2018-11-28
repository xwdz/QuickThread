package com.github.xwdz.quickthread;


import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class QuietCallable<V> implements Callable<V> {

    private static AtomicInteger PID = new AtomicInteger(1);
    private static final String DEFAULT_THREAD_NAME = "Quick@" + PID.getAndIncrement();

    public final String mName;

    public QuietCallable() {
        this(DEFAULT_THREAD_NAME);
    }

    public QuietCallable(String name) {
        mName = name;
    }

    @Override
    public V call() throws Exception {
        final Thread thread = Thread.currentThread();
        thread.setName(mName);
        return qCall();
    }


    public abstract V qCall() throws Exception;

}
