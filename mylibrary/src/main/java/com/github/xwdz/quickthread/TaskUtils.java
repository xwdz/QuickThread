package com.github.xwdz.quickthread;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author 黄兴伟 (xwd9989@gamil.com)
 * @since 2018/6/9
 */
public class TaskUtils {


    private static final class Holder {
        private static final TaskUtils INSTANCE = new TaskUtils();
    }

    public static TaskUtils get() {
        return Holder.INSTANCE;
    }

    private ScheduledExecutorService mScheduledExecutorService;

    private TaskUtils() {
        mScheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    }

    public void schedule(Runnable command, long delay, TimeUnit unit) {
        mScheduledExecutorService.schedule(command, delay, unit);
    }

    public void schedule(Runnable command, long delay) {
        mScheduledExecutorService.schedule(command, delay, TimeUnit.MILLISECONDS);
    }

    public void awaitTermination(long timeout, TimeUnit timeUnit) throws InterruptedException {
        mScheduledExecutorService.awaitTermination(timeout, timeUnit);
    }

    public void shutdown() {
        mScheduledExecutorService.shutdown();
    }

    public <T> Future<T> async(Callable<T> task, final Response<T> responseListener) {
        final Future<T> future = mScheduledExecutorService.submit(task);
        mScheduledExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    T t = future.get();
                    if (responseListener != null) {
                        responseListener.onSuccess(t);
                    }
                } catch (Throwable e) {
                    if (responseListener != null) {
                        responseListener.onError(e);
                    }
                }
            }
        });
        return future;

    }

    public List<Runnable> shutdownNow() {
        return mScheduledExecutorService.shutdownNow();
    }

    public <V> ScheduledFuture<V> schedule(Callable<V> command, long delay, TimeUnit unit) {
        return mScheduledExecutorService.schedule(command, delay, unit);
    }

    public void scheduleWithFixedDelay(Runnable command, long initialDelay, long delay) {
        mScheduledExecutorService.scheduleWithFixedDelay(command, initialDelay, delay, TimeUnit.MILLISECONDS);
    }

    public void scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
        mScheduledExecutorService.scheduleWithFixedDelay(command, initialDelay, delay, unit);
    }
}


