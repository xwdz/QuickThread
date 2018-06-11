package com.github.xwdz.quickthread;


import com.github.xwdz.quickthread.callback.GlobalCallback;

import java.util.concurrent.Callable;

/**
 * @author huangxingwei (xwd9989@gamil.com)
 * @since 2018/6/9
 */
public final class CallableWrapper<T> implements Callable<T> {

    private Callable<T> mProxy;
    private GlobalCallback mGlobalCallback;
    private String mName;

    public CallableWrapper(String name, Callable<T> proxy, GlobalCallback globalCallback) {
        this.mName = name;
        this.mProxy = proxy;
        this.mGlobalCallback = globalCallback;
    }

    @Override
    public T call() throws Exception {
        final Thread thread = Thread.currentThread();
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                if (mGlobalCallback != null) {
                    mGlobalCallback.onError(mName, e);
                }
            }
        });


        if (mGlobalCallback != null) {
            mGlobalCallback.onStart(mName);
        }

        T t = mProxy == null ? null : mProxy.call();
        if (mGlobalCallback != null) {
            mGlobalCallback.onCompleted(mName);
        }
        return t;
    }
}
