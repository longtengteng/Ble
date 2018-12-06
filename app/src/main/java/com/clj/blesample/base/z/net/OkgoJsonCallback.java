package com.clj.blesample.base.z.net;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.view.Window;

import com.alibaba.fastjson.JSON;
import com.liuniukeji.singemall.R;
import com.liuniukeji.singemall.util.utilcode.LogUtils;
import com.liuniukeji.singemall.util.utilcode.ToastUtils;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.exception.HttpException;
import com.lzy.okgo.exception.StorageException;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.UnknownHostException;

import okhttp3.ResponseBody;

/**
 * Copyright (c) 山东六牛网络科技有限公司 https://liuniukeji.com
 *
 * @Description
 * @Author 与天同行的观测者
 * @Copyright Copyright (c) 山东六牛网络科技有限公司 保留所有版权(https://www.liuniukeji.com)
 * @Date 2017/9/16 0016 14:20
 * @CreateBy Administrator
 */

public class OkgoJsonCallback<T> extends AbsCallback<T> {
    private Context context;
    private ProgressDialog dialog;
    private boolean canShow;
    private View loadingView;
    private View errorView;

    public OkgoJsonCallback() {
    }

    public OkgoJsonCallback(Context context, boolean canShow) {
        this.context = context;
        this.canShow = canShow;
        dialog = new ProgressDialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage(context.getString(R.string.connecting));
    }

    /**
     * 有dialog,有错误view
     *
     * @param context
     * @param canShow
     * @param errorView
     */
    public OkgoJsonCallback(Context context, boolean canShow, View errorView) {
        this.context = context;
        this.canShow = canShow;
        this.errorView = errorView;
        dialog = new ProgressDialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage(context.getString(R.string.connecting));
    }

    /**
     * 有加载view,有错误view
     *
     * @param context
     * @param loadingView
     * @param errorView
     */
    public OkgoJsonCallback(Context context, View loadingView, View errorView) {
        this.context = context;
        this.loadingView = loadingView;
        this.errorView = errorView;
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);
        try {
            if (dialog != null && !dialog.isShowing() && canShow) {
                dialog.show();
                dialog.getWindow().setDimAmount(0f);
            }

            if (loadingView != null && loadingView.getVisibility() != View.VISIBLE) {
                loadingView.setVisibility(View.VISIBLE);
            }

            if (errorView != null && errorView.getVisibility() != View.GONE) {
                errorView.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Response<T> response) {

        try {
            if (dialog != null && dialog.isShowing() && canShow) {
                dialog.dismiss();
            }

            if (loadingView != null && loadingView.getVisibility() != View.GONE) {
                loadingView.setVisibility(View.GONE);
            }

            if (errorView != null && errorView.getVisibility() != View.VISIBLE) {
                errorView.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Throwable exception = response.getException();
        if (exception != null) exception.printStackTrace();
        String message = "";
        if (exception instanceof UnknownHostException || exception instanceof ConnectException) {
            LogUtils.e("网络连接失败，请连接网络！");
            ToastUtils.showShort("网络连接失败，请连接网络！");
            message = "网络连接失败，请连接网络！";
        } else if (exception instanceof SocketException) {
            LogUtils.e("网络请求超时");
            ToastUtils.showShort("网络请求超时");
            message = "网络请求超时";
        } else if (exception instanceof HttpException) {
            LogUtils.e("服务端响应码404和500");
            ToastUtils.showShort("服务端响应码404和500");
            message = "服务端响应码404和500";
        } else if (exception instanceof StorageException) {
            LogUtils.e("sd卡不存在或者没有权限");
            ToastUtils.showShort("sd卡不存在或者没有权限");
            message = "sd卡不存在或者没有权限";
        } else if (exception instanceof IllegalStateException) {
            message = exception.getMessage();
            ToastUtils.showShort(message);
            LogUtils.e(message);
        }
        response.setException(exception);
        super.onError(response);
    }

    @Override
    public void onSuccess(Response<T> response) {

    }

    @Override
    public T convertResponse(okhttp3.Response response) throws Throwable {
        T data = null;
        try {
            ResponseBody body = response.body();
            if (body == null)
                return null;
            data = null;
            Type genType = getClass().getGenericSuperclass();
            Type type = ((ParameterizedType) genType).getActualTypeArguments()[0];
            data = JSON.parseObject(body.string(), type);
        } catch (IOException e) {
            return null;
        }
        return data;
    }
}
