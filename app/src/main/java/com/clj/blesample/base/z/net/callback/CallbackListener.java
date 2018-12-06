/*
 * Copyright © Yan Zhenjie. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.clj.blesample.base.z.net.callback;

import android.content.Context;

import com.liuniukeji.singemall.base.z.helper.DialogManager;

/**
 * Copyright (c) 山东六牛网络科技有限公司 https://liuniukeji.com
 *
 * @Description 网络回调
 * @Author zhugefubin qq:553629767;
 * @Copyright Copyright (c) 山东六牛网络科技有限公司 保留所有版权(https://www.liuniukeji.com)
 * @Date 2018/4/27 0027 14:32
 * @CreateBy Android Studio
 * @ModifiedBy
 */

public abstract class CallbackListener<T> {
    private Context ctxToShowDialog;

    public CallbackListener() {
    }


    public Context getCtx() {
        return ctxToShowDialog;
    }

    public CallbackListener(Context ctx) {
        this.ctxToShowDialog = ctx;
    }


    public void onStart() {
        if (ctxToShowDialog != null) {
            DialogManager.showDialog(ctxToShowDialog);
        }
    }

    public void onSucceed(T response) {
        closeDialog();
    }

    public void onFailed(T response) {
        closeDialog();
    }

    public void onFinish() {
        closeDialog();
    }

    private void closeDialog() {
        DialogManager.dissmiss();
    }
}
