package com.example.quinn.quickthreadtest;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * @author 酸菜 (xwd9989@gamil.com)
 * @since 2018/6/8
 */
public class IfPossible {
    public static void main(String[] args){
        Observable.just("Marry me !!")
                .retryWhen(new Func1<Observable<? extends Throwable>, Observable<Long>>() {
                    @Override
                    public Observable<Long> call(Observable<? extends Throwable> throwableObservable) {
                        return throwableObservable.zipWith(Observable.range(1, Integer.MAX_VALUE),
                                new Func2<Throwable, Integer, Integer>() {
                                    @Override
                                    public Integer call(Throwable throwable, Integer i) {
                                        return i;
                                    }
                                }).concatMap(new Func1<Integer, Observable<? extends Long>>() {
                            @Override
                            public Observable<? extends Long> call(Integer retryCount) {
                                return Observable.timer((long) Math.pow(2, retryCount), TimeUnit.SECONDS);
                            }
                        });
                    }
                }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                //Never completed
            }
            @Override
            public void onError(Throwable e) {
                //No failure .. Never!
            }
            @Override
            public void onNext(String s) {
                //Endless  love
            }
        });
    }
}
