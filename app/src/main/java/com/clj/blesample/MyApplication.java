package com.clj.blesample;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.clj.blesample.util.Utils;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class MyApplication extends Application {
    static final String TAG = MyApplication.class.getSimpleName();
    private static final String MQ_APPKEY = "84bcda59d169f8ff7e2bb96a6e2582d5";//美洽 App Key
    static MyApplication instance;
    static Context context;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public synchronized static MyApplication getInstance() {
        if (null == instance) {
            instance = new MyApplication();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        context = this;
//        CrashHandler.getInstance().init(this);
        Utils.init(this);
    }


}