package com.example.mylibrary;

public class QuickThreadManager {

    private static QuickPool sNetwork;

    private static QuickPool sTest;

    static {
        sNetwork = new QuickPool.Builder()
                .createFixedThreadPool(5)
                .build();

        sTest = new QuickPool.Builder()
                .createScheduledThreadPool(5)
                .build();
    }

    public static QuickPool getNetwork() {
        return sNetwork;
    }

    public static QuickPool getTest() {
        return sTest;
    }

}
