package com.example.mylibrary;

/**
 * @author huangxingwei(xwdz9989@gmail.com)
 * @since 1.0.1
 */
public class QuickThreadManager {

    private static QuickPool sNetwork;

    private static QuickPool sTest;

    static {
        sNetwork = new QuickPool.Builder()
                .createFixed(5)
                .build();

        sTest = new QuickPool.Builder()
                .createScheduled(5)
                .build();
    }

    public static QuickPool getNetwork() {
        return sNetwork;
    }

    public static QuickPool getTest() {
        return sTest;
    }

}
