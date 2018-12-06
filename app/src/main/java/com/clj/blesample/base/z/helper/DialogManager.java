package com.clj.blesample.base.z.helper;

import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Window;

import com.liuniukeji.singemall.R;

/**
 * Copyright (c) 山东六牛网络科技有限公司 https://liuniukeji.com
 *
 * @Description
 * @Author zhugefubin tel:553629767;
 * @Copyright Copyright (c) 山东六牛网络科技有限公司 保留所有版权(https://www.liuniukeji.com)
 * @Date 2018-07-18 14:44
 * @CreateBy Android Studio
 * @ModifiedBy
 */

public class DialogManager {
    private static ProgressDialog dialog;

    public static ProgressDialog showDialog(Context context) {
        return showDialog(context, null);
    }

    public static ProgressDialog showDialog(Context context, String text) {
        if (context == null) {
            return dialog;
        }
        if (dialog != null && dialog.getContext() != null && dialog.isShowing()) {
            return dialog;
        }
        dialog = new ProgressDialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        if (TextUtils.isEmpty(text)) {
            dialog.setMessage(context.getString(R.string.connecting));
        } else {
            dialog.setMessage(text);
        }
        dialog.show();
        //背景透明
        dialog.getWindow().setDimAmount(0f);

        return dialog;
    }

    public static void dissmiss() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
    }
}
