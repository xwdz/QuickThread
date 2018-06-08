package com.example.mylibrary;

/**
 * @author huangxingwei(xwdz9989@gmail.com)
 * @since 1.0.0
 */
public abstract class QuickThread implements Runnable {

    private String mName;

    public QuickThread(String name) {
        mName = name;
    }

    @Override
    public void run() {
        Thread.currentThread().setName(mName);

        running();
    }

    public abstract void running();
}
