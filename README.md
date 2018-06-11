### QuickThread

```
//同步执行一个Call
Future future = QuickManager.getCache().sync(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(10000);
                return "this is test";
            }
        });

        try {
            Log.e("TAG","future = " + future.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
```

```
//异步执行一个call
        QuickManager.getCache().async(new Callable<String>() {
            @Override
            public String call() throws Exception {
                if (Looper.getMainLooper() == Looper.myLooper()) {
                    Log.e("TAG", "main Thread");
                } else {
                    Log.e("TAG", "child Thread");
                }
                Thread.sleep(10000);
                return "123123";
            }
        }, new Response<String>() {
            @Override
            public void onSuccess(String s) {
                Log.e("TAG", "s = " + s);
            }

            @Override
            public void onError(Throwable e) {
            }
        });
```


