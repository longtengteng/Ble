package com.clj.blesample.net;

import java.io.Serializable;

/**
 * ================================================
 * 作    者：jeasonlzy（廖子尧）Github地址：https://github.com/jeasonlzy
 * 版    本：1.0
 * 创建日期：16/9/28
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class SimpleResponse implements Serializable {

    private static final long serialVersionUID = -1477609349345966116L;

    public int status;
    public String info;

    public LazyResponse toLazyResponse() {
        LazyResponse lazyResponse = new LazyResponse();
        lazyResponse.status = status;
        lazyResponse.info = info;
        return lazyResponse;
    }
}