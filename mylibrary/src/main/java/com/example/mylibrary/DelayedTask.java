package com.example.mylibrary;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author 黄兴伟 (xwd9989@gamil.com)
 * @since 2018/6/9
 */
public class DelayedTask {


    private static final class Holder {
        private static final DelayedTask INSTANCE = new DelayedTask();
    }

    public static DelayedTask get() {
        return Holder.INSTANCE;
    }

    private ScheduledExecutorService mScheduledExecutorService;

    private DelayedTask() {
        mScheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    }

    public void schedule(Runnable command, long delay, TimeUnit unit) {
        mScheduledExecutorService.schedule(command, delay, unit);
    }

    public <V> ScheduledFuture<V> schedule(Callable<V> command, long delay, TimeUnit unit) {
        return mScheduledExecutorService.schedule(command, delay, unit);
    }

    public void scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
        mScheduledExecutorService.scheduleWithFixedDelay(command, initialDelay, delay, unit);

    }


}
