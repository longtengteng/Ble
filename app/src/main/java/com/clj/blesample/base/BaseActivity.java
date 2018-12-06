package com.clj.blesample.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.clj.blesample.util.ToastUtils;


/**
 * Copyright (c) 山东六牛网络科技有限公司 https://liuniukeji.com
 *
 * @Description
 * @Author         与天同行的观测者
 * @Copyright      Copyright (c) 山东六牛网络科技有限公司 保留所有版权(https://www.liuniukeji.com)
 * @Date           2017/12/11 0011 14:36
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected Context context;
    protected String TAG;
    protected InputMethodManager inputMethodManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        TAG = this.getClass().getSimpleName();
        context = this;
        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        initView();
    }

    private void initView() {
        loadViewLayout();
        findViewById();
        setListener();
        processLogic();
    }

    protected abstract void loadViewLayout();


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

    public void showToast(String msg) {
        ToastUtils.showShort(msg);
    }

    /**
     * 打开一个Activity 默认 不关闭当前activity
     */
    public void gotoActivity(Class<?> clz) {
        gotoActivity(clz, false, null);
    }

    public void gotoActivity(Class<?> clz, boolean isCloseCurrentActivity) {
        gotoActivity(clz, isCloseCurrentActivity, null);
    }

    public void gotoActivity(Class<?> clz, boolean isCloseCurrentActivity, Bundle ex) {
        Intent intent = new Intent(this, clz);
        if (ex != null) intent.putExtras(ex);
        startActivity(intent);
        if (isCloseCurrentActivity) {
            ((Activity) context).finish();
        }
    }

    protected void hideSoftKeyboard(View view) {
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    protected void showSoftKeyboard(View view) {
        inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }
}
