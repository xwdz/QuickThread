package com.github.xwdz.quickthread;


import java.util.concurrent.Callable;

public abstract class QuickCallable<V> implements Callable<V> {

    public final String mName;

    public QuickCallable(String name) {
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
