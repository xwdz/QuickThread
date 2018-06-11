### QuickThread


   //同步执行一个call
           Future syncFuture = QuickManager.getCache().sync(new QuickCallable<String>("test") {
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


