package com.github.xwdz.quickthread;

/**
 * @author 黄兴伟 (xwd9989@gamil.com)
 * @since 2018/6/9
 */
public interface Response<T> {

    void onSuccess(T t);

    void onError(Throwable e);
}
