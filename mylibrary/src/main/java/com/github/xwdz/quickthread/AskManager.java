package com.github.xwdz.quickthread;

/**
 * 全局线程池管理
 *
 * @author huangxingwei(xwdz9989@gmail.com)
 * @since 1.0.0
 */
public class AskManager {

    private static QuietPool sNetwork;

    private static QuietPool sIO;

    private static QuietPool sCache;

    private static QuietPool sSingle;


    static {
        sNetwork = new QuietPool.Builder()
                .createFixed(10)
                .build();

        sIO = new QuietPool.Builder()
                .createFixed(5)
                .build();


        sCache = new QuietPool.Builder()
                .createCached()
                .build();

        sSingle = new QuietPool.Builder().createSingle().build();
    }

    public static QuietPool getNetwork() {
        return sNetwork;
    }

    public static QuietPool getIO() {
        return sIO;
    }

    public static QuietPool getCache() {
        return sCache;
    }

    public static QuietPool getSingle() {
        return sSingle;
    }

}
