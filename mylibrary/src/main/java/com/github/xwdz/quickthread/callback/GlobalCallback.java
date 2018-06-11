package com.github.xwdz.quickthread.callback;

/**
 * @author huangxingwei (xwd9989@gamil.com)
 * @since 2018/6/9
 */
public interface GlobalCallback {

    void onStart(String threadName);

    void onCompleted(String threadName);

    void onError(String threadName,Throwable t);
}
