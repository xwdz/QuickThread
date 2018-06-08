package com.example.mylibrary;

import android.support.annotation.NonNull;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author huangxingwei(xwdz9989@gmail.com)
 * @since 1.0.0
 */
public final class QuickPool implements QuickExecutor {

    private ExecutorService mThreadPool;
    private ScheduledExecutorService mScheduledExecutorService;

    private QuickPool(Builder builder) {
        mScheduledExecutorService = builder.mScheduledExecutorService;
        mThreadPool = builder.mThreadPool;
    }

    @Override
    public void execute(@NonNull Runnable command) {
        mThreadPool.execute(command);
    }

    @Override
    public void delay(@NonNull Runnable command, long delay, TimeUnit unit) {
        if (mScheduledExecutorService != null) {
            mScheduledExecutorService.schedule(command, delay, unit);
        }
    }


    @Override
    public void scheduled(@NonNull Runnable command, long initialDelay, long delay, TimeUnit unit) {
        if (mScheduledExecutorService != null) {
            mScheduledExecutorService.scheduleWithFixedDelay(command, initialDelay, delay, unit);
        }
    }

    @Override
    public <V> ScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit) {
        if (mScheduledExecutorService != null) {
            return mScheduledExecutorService.schedule(callable, delay, unit);
        }
        return null;
    }


    public static class Builder {

        private ExecutorService mThreadPool;
        private ScheduledExecutorService mScheduledExecutorService;

        public Builder() {
        }

        public Builder createCached() {
            mThreadPool = Executors.newCachedThreadPool();
            return this;
        }

        public Builder createFixed(int sThread) {
            mThreadPool = Executors.newFixedThreadPool(sThread);
            return this;
        }

        public Builder createScheduled(int coreThreadSize) {
            mThreadPool = Executors.newScheduledThreadPool(coreThreadSize);
            return this;
        }

        public Builder createSingle() {
            mThreadPool = Executors.newSingleThreadExecutor();
            return this;
        }

        public QuickPool build() {
            mScheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
            return new QuickPool(this);
        }

    }
}
