package com.clj.blesample.base;

import android.support.annotation.NonNull;

/**
 * Copyright (c) 山东六牛网络科技有限公司 https://liuniukeji.com
 * 
 * @Description    
 * @Author         与天同行的观测者
 * @Copyright      Copyright (c) 山东六牛网络科技有限公司 保留所有版权(https://www.liuniukeji.com)
 * @Date           2017/12/11 0011 14:36
 */
public interface BasePresenter<T extends BaseView> {

  void attachView(@NonNull T view);

  void detachView();
}
