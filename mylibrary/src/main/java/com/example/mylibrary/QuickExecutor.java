package com.example.mylibrary;

import android.support.annotation.NonNull;

import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 执行任务接口
 * @author huangxingwei(xwdz989@gmail.com)
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
     * 延时执行一个任务
     *
     * @param command
     */
    void delay(@NonNull Runnable command,long delay, TimeUnit unit);

    /**
     * 延时执行一个周期性任务
     */
    void scheduled(@NonNull Runnable command,long initialDelay,long delay, TimeUnit unit);


    /**
     * 支持Callable接口
     */
    <V> ScheduledFuture<V> schedule(Callable<V> callable,
                                    long delay, TimeUnit unit);
}
