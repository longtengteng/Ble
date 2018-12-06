package com.clj.blesample.net;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.liuniukeji.singemall.R;
import com.liuniukeji.singemall.ui.account.LoginAndRegisterActivity;
import com.liuniukeji.singemall.ui.main.MainActivity;
import com.liuniukeji.singemall.util.utilcode.LogUtils;
import com.liuniukeji.singemall.util.utilcode.ToastUtils;
import com.liuniukeji.singemall.util.utilcode.Utils;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.exception.HttpException;
import com.lzy.okgo.exception.StorageException;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

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
 * @Date 2017/12/11 0011 14:12
 */

public class JsonCallback<T> extends AbsCallback<T> {
    private Context context;
    private ProgressDialog dialog;
    private boolean canShow;
    private View loadingView;
    private View errorView;

    public JsonCallback() {

    }

    public JsonCallback(Context context, boolean canShow) {
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
    public JsonCallback(Context context, boolean canShow, View errorView) {
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
    public JsonCallback(Context context, View loadingView, View errorView) {
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
        super.onError(response);
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
        if (exception instanceof UnknownHostException || exception instanceof ConnectException) {
            ToastUtils.showShort("网络连接失败，请连接网络！");
        } else if (exception instanceof SocketException) {
            ToastUtils.showShort("网络请求超时！");
        } else if (exception instanceof HttpException) {
            ToastUtils.showShort("服务端响应码404和500！");
        } else if (exception instanceof StorageException) {
            LogUtils.e("sd卡不存在或者没有权限");
            ToastUtils.showShort("sd卡不存在或者没有权限！");
        } else if (exception instanceof IllegalStateException) {
            String message = exception.getMessage();
            ToastUtils.showShort(message + "");
        }
    }

    @Override
    public void onSuccess(Response<T> response) {
        try {
            if (dialog != null && dialog.isShowing() && canShow) {
                dialog.dismiss();
            }

            if (loadingView != null && loadingView.getVisibility() != View.GONE) {
                loadingView.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public T convertResponse(okhttp3.Response response) throws Throwable {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        Type type = params[0];
        if (!(type instanceof ParameterizedType)) throw new IllegalStateException("没有填写泛型参数");
        Type rawType = ((ParameterizedType) type).getRawType();
        Type typeArgument = ((ParameterizedType) type).getActualTypeArguments()[0];
        ResponseBody body = response.body();
        response.close();
        if (body == null) return null;
        String mResponse = body.string();
        Gson gson = new Gson();
        if (rawType != LazyResponse.class) {
            T data = gson.fromJson(mResponse, type);
            return data;
        } else {
            if (typeArgument == Void.class) {
                SimpleResponse simpleResponse = gson.fromJson(mResponse, SimpleResponse.class);
                int code = JSON.parseObject(mResponse).getInteger("status");
                if (code == 1) {
                    return (T) simpleResponse.toLazyResponse();
                } else {
                    throw new IllegalStateException(simpleResponse.info);
                }
            } else {
                int code = JSON.parseObject(mResponse).getInteger("status");
                String data = JSON.parseObject(mResponse).getString("data");
                String info = JSON.parseObject(mResponse).getString("info");
                if (code == 1) {
                    if (TextUtils.isEmpty(data)) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("status", code);
                        jsonObject.put("info", info);
                        jsonObject.put("data", null);
                        mResponse = jsonObject.toString();
//                        throw new Exception(JSON.parseObject(mResponse).getString("info"));
                    }
                    LazyResponse lazyResponse = gson.fromJson(mResponse, type);
                    return (T) lazyResponse;
                } else if (code == 2) {
                    Intent intent = new Intent(Utils.getApp(), MainActivity.class);
                    intent.putExtra("isLogin", true);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Utils.getApp().startActivity(intent);
                    throw new IllegalStateException(JSON.parseObject(mResponse).getString("info"));
                } else if (code == -1) {
                    Intent intent = new Intent(Utils.getApp(), LoginAndRegisterActivity.class);
                    intent.putExtra("isLogin", true);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Utils.getApp().startActivity(intent);
                    throw new IllegalStateException(JSON.parseObject(mResponse).getString("info"));
                } else {
                    throw new IllegalStateException(JSON.parseObject(mResponse).getString("info"));
                }
            }
        }
    }
}
