package com.example.mylibrary;

import android.support.annotation.NonNull;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author 黄兴伟 (xwd9989@gamil.com)
 * @since 2018/6/9
 */
public class FutureWrapper implements Future {

    private final Future mFuture;

    public FutureWrapper(Future result) {
        mFuture = result;
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return mFuture.cancel(mayInterruptIfRunning);
    }

    @Override
    public boolean isCancelled() {
        return mFuture.isCancelled();
    }

    @Override
    public boolean isDone() {
        return mFuture.isDone();
    }

    @Override
    public Object get() throws InterruptedException, ExecutionException {
        return mFuture.get();
    }

    @Override
    public Object get(long timeout, @NonNull TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return mFuture.get(timeout, unit);
    }
}
