package com.clj.blesample;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.clj.blesample.model.Constants;
import com.clj.blesample.model.UserinfoModel;
import com.clj.blesample.util.SPUtils;
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
    public static String getToken() {
        if (hasToken()) {
            return getInstance().getUser().getToken();
        } else {
            //ToastUtils.showShort("登录状态丢失,请重新登录");
            // Exit(getInstance());
            return "";
        }
    }
    public static boolean hasToken() {
        UserinfoModel u = MyApplication.getInstance().getUser();
        if (u != null) {
            String token = u.getToken();
            if (TextUtils.isEmpty(token)) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    public UserinfoModel getUser() {
        String s = SPUtils.getInstance().getString(Constants.USERINFO);
        if (TextUtils.isEmpty(s)) {
            return null;
        }
        UserinfoModel loginBean = JSON.parseObject(s, UserinfoModel.class);
        return loginBean;
    }


}