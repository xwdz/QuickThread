### QuickThread

### 特性

- 任务扩展:支持延迟任务以及异步回调任务;
- 回调通知:当任务启动时与任务运行完毕后,有分别的生命周期作为通知;
- 线程切换:可制定是否回调主线程;
- 使用安全:当线程出现异常。能自动将catch异常信息传递给用户，避免出现crash;


### 依赖
> compile 'com.xwdz:QuickThread:0.0.2'


### 用法与原生线程池并无区别

####  Runnable任务

```
QuickPool quickPool  = QuickManager.getNetwork();
        quickPool.execute(new Runnable() {
            @Override
            public void run() {
                
            }
        });
```

#### 同步Callable任务

```
Future syncFuture = QuickManager.getCache().submit(new QuickCallable<String>("test") {
       @Override
       public String qCall() throws Exception {
           Thread.sleep(10000);
           return "this is Test";
       }
   });

   try {
       String result = (String) syncFuture.get();
   } catch (Exception e) {
       e.printStackTrace();
   }
```

#### 异步Callable任务

**重点说一下第三个参数**

- isMainUICallback ： `是否回调在主线程`

```
QuickManager.getCache().async(new QuickCallable<String>("name") {
            @Override
            public String qCall() throws Exception {
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
        }, true);
```



