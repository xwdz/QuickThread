package com.github.xwdz.quickthread;

import android.support.annotation.NonNull;

import com.github.xwdz.quickthread.callback.GlobalCallback;
import com.github.xwdz.quickthread.callback.Response;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 执行任务接口
 *
 * @author huangxingwei(xwdz989@gmail.com)
 * @since 1.0.0
 */
public interface QuietThreadExecutor {

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


    void setGlobalCallback(GlobalCallback callback);


    void awaitTermination(long timeout, TimeUnit timeUnit) throws InterruptedException;

    /**
     * 同步执行一个task
     *
     * @param task 支持callable接口
     * @param <T>
     * @return
     */
    <T> Future<T> submit(QuietCallable<T> task);

    /**
     * 同步执行一个task
     *
     * @param task 支持runnable接口
     * @return
     */
    Future<?> submit(Runnable task);

    /**
     * 异步执行一个任务
     *
     * @param task             command
     * @param responseListener 回调
     */
    <T> Future<T> async(QuietCallable<T> task, Response<T> responseListener);


    /**
     * 异步执行一个任务
     *
     * @param task             command
     * @param responseListener 回调
     * @param isMainUICallback 是否回调在主线程
     * @return
     */
    <T> Future<T> async(QuietCallable<T> task, Response<T> responseListener, boolean isMainUICallback);

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
