package com.clj.blesample.base.z.net;

import com.liuniukeji.singemall.MyApplication;
import com.liuniukeji.singemall.base.z.net.callback.CallbackListener;
import com.liuniukeji.singemall.util.utilcode.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import java.io.File;

/**
 * Copyright (c) 山东六牛网络科技有限公司 https://liuniukeji.com
 *
 * @Description
 * @Author zhugefubin tel:18615680590;
 * @Copyright Copyright (c) 山东六牛网络科技有限公司 保留所有版权(https://www.liuniukeji.com)
 * @Date 2018-04-08 15:42
 * @CreateBy Android Studio
 * @ModifiedBy
 */

public class Net {

    private static Net instance;

    private Net() {
    }

    public static Net getInstance() {
        if (instance == null) {
            instance = new Net();
        }
        return instance;
    }


    public <T> void post(String url, String[] keys, Object[] values, final CallbackListener<ResponseResult> mListener) {
        if (mListener != null) {
            if (mListener.getCtx() == null) {
//                   mListener.setShowWaitView(App.i().getCurrentActivity(), true);
            }
        }

        /*okGo*/
        HttpParams httpParams = new HttpParams();
        String token = getToken();
        //统一添加token
        httpParams.put("token", token);
        if (keys != null && values != null && keys.length == values.length) {
            for (int i = 0; i < keys.length; i++) {
                if (values[i] instanceof File) {
                    httpParams.put(keys[i], (File) values[i]);
                } else {
                    httpParams.put(keys[i], String.valueOf(values[i]));
                }
            }
        } else {
            try {
                throw new Exception("keys count not match values");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            OkGo.<ResponseResult>post(url).tag(url).params(httpParams).execute(new OkgoJsonCallback<ResponseResult>() {
                @Override
                public void onSuccess(Response<ResponseResult> response) {
                    super.onSuccess(response);
                    ResponseResult result = response.body();
                    if (result != null) {//判断后台抽风的问题
                        if (result.getStatus() == 1) {
                            if (mListener != null) {
                                mListener.onSucceed(result);
                            }
                        } else {
                            if (mListener != null) {
                                mListener.onFailed(result);
                            }
                            if (result.getStatus() == -1 || (result.getStatus() == 0 && (result.getInfo() + "").contains("用户已失效"))) {
                                goToLogin();//重新登录方法
                                ToastUtils.showShort(result.getInfo());
                            }
                        }
                    } else {
                        Throwable ex = response.getException();
                        if (ex != null) {
                            String message = ex.getMessage();
                            result = new ResponseResult(0, "errorInfo:" + message, "");
                        } else {
                            result = new ResponseResult(0, "errorInfo:" + response.message(), "");
                        }
                        if (mListener != null) {
                            mListener.onFailed(result);
                        }
                    }
                }

                @Override
                public void onError(Response<ResponseResult> response) {
                    super.onError(response);
                    ResponseResult result = response.body();
                    if (result == null) {
                        Throwable ex = response.getException();
                        if (ex != null) {
                            String message = ex.getMessage();
                            result = new ResponseResult(0, "errorInfo:" + message, "");
                        } else {
                            result = new ResponseResult(0, "errorInfo:" + response.message(), "");
                        }
                    }
                    if (mListener != null) {
                        mListener.onFailed(result);
                    }
                }

                @Override
                public void onFinish() {
                    super.onFinish();
                    if (mListener != null) {
                        mListener.onFinish();
                    }
                }

                @Override
                public void onStart(Request<ResponseResult, ? extends Request> request) {
                    super.onStart(request);
                    if (mListener != null) {
                        mListener.onStart();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //重新登录方法
    private void goToLogin() {
        MyApplication.Exit(MyApplication.getInstance());
    }

    //获取token
    private String getToken() {
        return MyApplication.getToken();
    }

    public void cancel(Object tag) {
        OkGo.getInstance().cancelTag(tag);
    }
}
