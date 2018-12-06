package com.clj.blesample.model;

/**
 * Copyright (c) 山东六牛网络科技有限公司 https://liuniukeji.com
 *
 * @Description
 * @Author zhugefubin tel:553629767;
 * @Copyright Copyright (c) 山东六牛网络科技有限公司 保留所有版权(https://www.liuniukeji.com)
 * @Date 2018-08-07 14:44
 * @CreateBy Android Studio
 * @ModifiedBy
 */

public class LoginEvent {
    public static final int WECHAT = 0x12;
    public static final int WEIBO = 0x13;
    int type = WECHAT;//0微信登录 1微博登录

    public LoginEvent() {
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public LoginEvent(int type) {
        this.type = type;
    }
}
