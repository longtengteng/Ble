package com.clj.blesample.base.z.mvp;

import android.content.Context;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public interface BaseView {
    Context getContext();

    void onSuccess(String msg);

    void onFailed(String msg);

    void onMessage(String msg);


}
