package com.github.xwdz.quickthread;

import android.support.annotation.NonNull;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author huangxingwei(xwdz9989@gmail.com)
 * @since 1.0.0
 */
public final class QuickPool implements QuickExecutor {

    private ExecutorService mThreadPool;
    private TaskUtils mTaskUtils;

    private QuickPool(Builder builder) {
        mThreadPool = builder.mThreadPool;
        mTaskUtils = TaskUtils.get();
    }

    @Override
    public void execute(@NonNull Runnable command) {
        mThreadPool.execute(command);
    }

    @Override
    public void delay(@NonNull Runnable command, long delay) {
        mTaskUtils.schedule(command, delay);
    }

    @Override
    public void delay(@NonNull Runnable command, long delay, TimeUnit unit) {
        mTaskUtils.schedule(command, delay, unit);
    }

    @Override
    public void scheduled(@NonNull Runnable command, long initialDelay, long delay) {
        mTaskUtils.scheduleWithFixedDelay(command, initialDelay, delay);
    }

    @Override
    public void scheduled(@NonNull Runnable command, long initialDelay, long delay, TimeUnit unit) {
        mTaskUtils.scheduleWithFixedDelay(command, initialDelay, delay, unit);
    }

    @Override
    public void awaitTermination(long timeout, TimeUnit timeUnit) throws InterruptedException {
        mTaskUtils.awaitTermination(timeout, timeUnit);
    }

    @Override
    public <T> Future<T> async(Callable<T> task, Response<T> responseListener) {
        return mTaskUtils.async(task, responseListener);
    }

    @Override
    public void shutdown() {
        mTaskUtils.shutdown();
    }

    @Override
    public List<Runnable> shutdownNow() {
        return mTaskUtils.shutdownNow();
    }

    @Override
    public <T> Future<T> sync(Callable<T> task) {
        return mThreadPool.submit(task);
    }

    @Override
    public Future<?> sync(Runnable task) {
        return mThreadPool.submit(task);
    }

    @Override
    public <V> ScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit) {
        return mTaskUtils.schedule(callable, delay, unit);
    }


    public static class Builder {

        private ExecutorService mThreadPool;

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

        public Builder createFiexed(int sThread, ThreadFactory factory) {
            mThreadPool = Executors.newFixedThreadPool(sThread, factory);
            return this;
        }

        public Builder createScheduled(int coreThreadSize) {
            mThreadPool = Executors.newScheduledThreadPool(coreThreadSize);
            return this;
        }

        public Builder createScheduled(int coreThreadSize, ThreadFactory factory) {
            mThreadPool = Executors.newScheduledThreadPool(coreThreadSize, factory);
            return this;
        }

        public Builder createSingle() {
            mThreadPool = Executors.newSingleThreadExecutor();
            return this;
        }

        public Builder createSingle(ThreadFactory factory) {
            mThreadPool = Executors.newSingleThreadExecutor(factory);
            return this;
        }

        public QuickPool build() {
            return new QuickPool(this);
        }

    }
}
