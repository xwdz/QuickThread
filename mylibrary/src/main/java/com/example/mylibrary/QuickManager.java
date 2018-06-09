package com.example.mylibrary;

/**
 * @author huangxingwei(xwdz9989@gmail.com)
 * @since 1.0.0
 */
public class QuickManager {

    private static QuickPool sNetwork;

    private static QuickPool sIO;

    private static QuickPool sCache;

    private static QuickPool sSingle;


    static {
        sNetwork = new QuickPool.Builder()
                .createFixed(10)
                .build();

        sIO = new QuickPool.Builder()
                .createFixed(5)
                .build();


        sCache = new QuickPool.Builder()
                .createCached()
                .build();

        sSingle = new QuickPool.Builder().createSingle().build();
    }

    public static QuickPool getNetwork() {
        return sNetwork;
    }

    public static QuickPool getIO() {
        return sIO;
    }

    public static QuickPool getCache() {
        return sCache;
    }

    public static QuickPool getSingle() {
        return sSingle;
    }

}
