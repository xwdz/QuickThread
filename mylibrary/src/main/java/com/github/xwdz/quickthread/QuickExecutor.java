package com.github.xwdz.quickthread;

import android.support.annotation.NonNull;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 执行任务接口
 *
 * @author huangxingwei(xwdz989 @ gmail.com)
 * @since 1.0.0
 */
public interface QuickExecutor {

    /**
     * 立即执行一个任务
     *
     * @param command
     */
    void execute(@NonNull Runnable command);

    /**
     * 延时执行一个任务，默认单位毫秒
     *
     * @param command runnable
     * @param delay   时间
     */
    void delay(@NonNull Runnable command, long delay);

    /**
     * 延时执行一个任务
     *
     * @param command runnable
     * @param delay   时间
     * @param unit    时间单位
     */
    void delay(@NonNull Runnable command, long delay, TimeUnit unit);


    /**
     * 延时执行一个周期性任务，默认时间单位毫秒
     *
     * @param command      runnable
     * @param initialDelay 延迟时间，这个时间之后才开始执行runnable
     * @param delay        时间
     */
    void scheduled(@NonNull Runnable command, long initialDelay, long delay);

    /**
     * 延时执行一个周期性任务
     *
     * @param command      runnable
     * @param initialDelay 延迟时间，这个时间之后才开始执行runnable
     * @param delay        时间
     * @param unit         时间单位
     */
    void scheduled(@NonNull Runnable command, long initialDelay, long delay, TimeUnit unit);

    void awaitTermination(long timeout, TimeUnit timeUnit) throws InterruptedException;

    /**
     * 同步执行一个task
     * @param task 支持callable接口
     * @param <T>
     * @return
     */
    <T> Future<T> sync(Callable<T> task);

    /**
     * 同步执行一个task
     * @param task 支持runnable接口
     * @return
     */
    Future<?> sync(Runnable task);

    /**
     * 异步执行一个任务
     * @param task command
     * @param responseListener 回调
     */
    <T> Future<T> async(Callable<T> task, Response<T> responseListener);

    /**
     * 线程池停止
     */
    void shutdown();

    List<Runnable> shutdownNow();


    /**
     * 支持Callable接口
     */
    <V> ScheduledFuture<V> schedule(Callable<V> callable,
                                    long delay, TimeUnit unit);
}
