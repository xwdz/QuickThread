package com.example.mylibrary;

import android.support.annotation.NonNull;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public final class QuickPool implements QuickExecutor {

    private Builder mBuilder;
    private ExecutorService mThreadPool;
    private ScheduledExecutorService mScheduledExecutorService;

    private QuickPool(Builder builder) {
        mBuilder = builder;
        mThreadPool = builder.mThreadPool;
    }

    @Override
    public void execute(@NonNull Runnable command) {
        mThreadPool.execute(command);
    }

    @Override
    public void delay(@NonNull Runnable command, long delay, TimeUnit unit) {
        createScheduledExecutor();

        mScheduledExecutorService.schedule(command, delay, unit);
    }

    @Override
    public void scheduled(@NonNull Runnable command, long initialDelay, long delay, TimeUnit unit) {
        mScheduledExecutorService.scheduleWithFixedDelay(command, initialDelay, delay, unit);
    }

    private void createScheduledExecutor() {
        if (mScheduledExecutorService == null) {
            mBuilder.createSingleThreadScheduledExecutor();
            mScheduledExecutorService = mBuilder.mScheduledExecutorService;
        }
    }

    @Override
    public <V> ScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit) {
        return null;
    }


    public static class Builder {

        private ExecutorService mThreadPool;
        private ScheduledExecutorService mScheduledExecutorService;

        public Builder() {
        }

        public Builder createCachedThreadPool() {
            mThreadPool = Executors.newCachedThreadPool();
            return this;
        }

        public Builder createFixedThreadPool(int sThread) {
            mThreadPool = Executors.newFixedThreadPool(sThread);
            return this;
        }

        public Builder createScheduledThreadPool(int coreThreadSize) {
            mThreadPool = Executors.newScheduledThreadPool(coreThreadSize);
            return this;
        }

        public Builder createSingleThreadPool() {
            mThreadPool = Executors.newSingleThreadExecutor();
            return this;
        }

        public Builder createSingleThreadScheduledExecutor() {
            mScheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
            return this;
        }

        public QuickPool build() {
            return new QuickPool(this);
        }

    }
}
