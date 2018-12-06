package com.clj.blesample.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Copyright (c) 山东六牛网络科技有限公司 https://liuniukeji.com
 *
 * @Description
 * @Author 与天同行的观测者
 * @Copyright Copyright (c) 山东六牛网络科技有限公司 保留所有版权(https://www.liuniukeji.com)
 * @Date $date$ $time$
 */

public abstract class BaseDialog extends Dialog {
    Context mContext;

    public BaseDialog(@NonNull Context context) {
        super(context);
    }

    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public BaseDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        setContentView(getLayoutInflater().inflate(loadViewLayout(), null));
        findViewById();
        setListener();
        processLogic();
    }

    protected abstract int loadViewLayout();

    /**
     * @描述 根据ID查找控件
     */
    protected void findViewById() {
    }

    /**
     * @描述 设置监听
     */
    protected void setListener() {
    }

    /**
     * @描述 处理业务
     */
    protected abstract void processLogic();
}
