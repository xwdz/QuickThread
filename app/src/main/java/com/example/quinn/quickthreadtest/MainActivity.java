package com.example.quinn.quickthreadtest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    private final int WHAT = 0x123;
    private static final String TAG = "TAG";


    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Log.e(TAG, "receiver message");
            return true;
        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ActivityWatcher activityWatcher = new ActivityWatcher() {
//            @Override
//            protected void onAppLaunched(String pkgName) {

        //}
        //};
        //activityWatcher.start();

//
//        QuickPool quickPool = QuickManager.getNetwork();
//        quickPool.setGlobalCallback(new GlobalCallback() {
//            @Override
//            public void onStart(String threadName) {
//
//            }
//
//            @Override
//            public void onCompleted(String threadName) {
//
//            }
//
//            @Override
//            public void onError(String threadName, Throwable t) {
//
//            }
//        });
//
//
//        QuickManager.getIO().delay(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        }, 1000, TimeUnit.SECONDS);
//
//
//
//        Log.e("TAG", "---feature init ");
//
//
//        //同步执行一个call
//        Future syncFuture = QuickManager.getCache().submit(new QuickCallable<String>("test") {
//            @Override
//            public String qCall() throws Exception {
//                Thread.sleep(10000);
//                return "this is Test";
//            }
//        });
//
//        try {
//            String result = (String) syncFuture.get();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        //异步执行一个call
//        QuickManager.getCache().async(new QuickCallable<String>("name") {
//            @Override
//            public String qCall() throws Exception {
//                if (Looper.getMainLooper() == Looper.myLooper()) {
//                    Log.e("TAG", "main Thread");
//                } else {
//                    Log.e("TAG", "child Thread");
//                }
//                Thread.sleep(10000);
//                return "123123";
//            }
//        }, new Response<String>() {
//            @Override
//            public void onSuccess(String s) {
//                Log.e("TAG", "s = " + s);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//            }
//        }, true);
    }
    public class MyRunnable implements Runnable {

        int threadNum;

        MyRunnable(int threadNum) {
            this.threadNum = threadNum;
        }

        @Override
        public void run() {
            Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
            Log.e("打印信息：", "等待中...执行第" + threadNum + "号线程");
            try {
                sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.e("打印信息：", "完成第" + threadNum + "号线程");
        }
    }
}
