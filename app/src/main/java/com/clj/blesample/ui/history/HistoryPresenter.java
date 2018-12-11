package com.clj.blesample.ui.history;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.clj.blesample.MyApplication;
import com.clj.blesample.net.JsonCallback;
import com.clj.blesample.net.LazyResponse;
import com.clj.blesample.net.UrlUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

public class HistoryPresenter implements HistoryContract.Present {
    Context context;
    HistoryContract.View mView;

    public HistoryPresenter(Context context) {
        this.context = context;
    }

    @Override
    public void index_noconnected() {
        String token = MyApplication.getToken();
        HttpParams httpParams = new HttpParams();
        httpParams.put("token", "163975257174261269");
        OkGo.<LazyResponse<HistoryBean>>post(UrlUtils.index_noconnected).tag(context).params(httpParams).execute(new JsonCallback<LazyResponse<HistoryBean>>(context, false) {
            @Override
            public void onSuccess(Response<LazyResponse<HistoryBean>> response) {
                super.onSuccess(response);
                if (mView != null)
                    mView.index_noconnected(response.body().getData());
            }

            @Override
            public void onError(Response<LazyResponse<HistoryBean>> response) {
                super.onError(response);
            }
        });
    }

    @Override
    public void attachView(@NonNull HistoryContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        OkGo.getInstance().cancelTag(context);
    }
}
