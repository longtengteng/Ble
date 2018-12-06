package com.clj.blesample.base.z.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Description 倒计时辅助类
 * @Author zhugefubin qq:553629767;
 * @Date 2018-05-22 08:39
 * @CreateBy Android Studio
 * @ModifiedBy
 */

public class SendCodeTextTimerHelper {
    private static final String DETAULT_TIP_TEXT = "还剩%d秒";
    static SendCodeTextTimerHelper timerUtils;
    private static SharedPreferences sp;

    public static synchronized SendCodeTextTimerHelper with(Context ctx) {
        if (timerUtils == null) {
            timerUtils = new SendCodeTextTimerHelper();
            sp = ctx.getSharedPreferences("SendCodeTextTimerHelper", Context.MODE_PRIVATE);
        }
        return timerUtils;
    }

    Map<String, Integer> map = new HashMap<>();//暂存数字
    Map<String, View> views = new HashMap<>();//暂存view

    /**
     * 用于加载剩余时间 退出重进继续计时
     *
     * @param tag      标签 用于区分不同的textview/button
     * @param textView 要操作的textview/button
     * @return
     */
    public SendCodeTextTimerHelper load(@NonNull final String tag, @NonNull final TextView textView) {
        return load(tag, textView, DETAULT_TIP_TEXT);
    }

    /**
     * * 用于加载剩余时间 退出重进继续计时
     *
     * @param timerTag 标签 用于区分不同的textview/button
     * @param textView 要操作的textview/button
     * @param tipText  提示文字 如:还剩%d秒(执行时格式化替换%d)
     */
    public SendCodeTextTimerHelper load(@NonNull String timerTag, @NonNull TextView textView, @NonNull String tipText) {
        if (!tipText.contains("%")) {
            Log.d("SendCodeTextTimerHelper", "未提供正确格式化字符串提示语( 如:还剩 %d 秒(执行时格式化替换 %d )),已用默认格式替换");
            tipText = DETAULT_TIP_TEXT;
        }
        int storeTime = sp.getInt(timerTag + textView.getId(), -1);
        if (storeTime > 0) {
            map.put(timerTag, storeTime);
            views.put(timerTag, textView);
            String oldText = textView.getText().toString();
            schedule(timerTag, oldText, tipText);
        } else {
            map.put(timerTag, storeTime);
        }
        return timerUtils;
    }

    public void startTimer(@NonNull String timerTag, @NonNull TextView textView, int second) {
        startTimer(timerTag, textView, second, DETAULT_TIP_TEXT);
    }

    /**
     * @param timerTag 标签 用于区分不同的textview/button
     * @param textView 要操作的textview/button
     * @param tipText  提示文字 如:还剩%d秒(执行时格式化替换%d)
     * @param second   要执行的秒数(单位:秒)
     */
    public void startTimer(@NonNull String timerTag, @NonNull TextView textView, int second, @NonNull String tipText) {
        if (!tipText.contains("%")) {
            Log.d("SendCodeTextTimerHelper", "未提供正确格式化字符串提示语,已用默认格式替换");
            tipText = DETAULT_TIP_TEXT;
        }
        map.put(timerTag, second);
        views.put(timerTag, textView);
        schedule(timerTag, textView.getText().toString(), tipText);
    }

    private void schedule(final String timerTag, final String oldText, final String finalTipText) {
        final TextView textView = (TextView) views.get(timerTag);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    //临时变量保存时间
                    int time = map.get(timerTag);
                    time--;
                    map.put(timerTag, time);
                    if (sp != null) {
                        sp.edit().putInt(timerTag + textView.getId(), time).apply();
                    } else {
                        cancel();
                    }
                    if (time <= 0) {
                        if (sp != null) {
                            sp.edit().putInt(timerTag + textView.getId(), 0).apply();
                        }
                        if (textView != null) {
                            textView.post(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setClickable(true);
                                    textView.setText(oldText);
                                    views.remove(timerTag);//移除已经完成的view
                                }
                            });
                        } else {
                            cancel();
                            return;
                        }
                        cancel();
                        return;
                    } else {
                        final int finalTime = time;
                        if (textView != null) {
                            textView.post(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setClickable(false);
                                    textView.setText(String.format(finalTipText, finalTime));
                                }
                            });
                        } else {
                            cancel();
                            return;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    sp.edit().putInt(timerTag + textView.getId(), -1).apply();
                    if (textView != null) {
                        textView.post(new Runnable() {
                            @Override
                            public void run() {
                                textView.setClickable(true);
                                textView.setText(oldText);
                                views.remove(timerTag);
                            }
                        });
                    }
                }
            }
        }, 1, 1000);//一秒倒计时一次
    }
}
