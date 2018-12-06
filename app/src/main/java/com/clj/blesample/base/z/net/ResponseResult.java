package com.clj.blesample.base.z.net;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * Copyright (c) 山东六牛网络科技有限公司 https://liuniukeji.com
 *
 * @Description
 * @Author zhugefubin tel:18615680590;
 * @Copyright Copyright (c) 山东六牛网络科技有限公司 保留所有版权(https://www.liuniukeji.com)
 * @Date 2018-04-08 15:56
 * @CreateBy Android Studio
 * @ModifiedBy
 */
public class ResponseResult {

    public ResponseResult(int status, String info, String data) {
        this.status = status;
        this.info = info;
        this.data = data;
    }

    public ResponseResult() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return TextUtils.isEmpty(info) ? "空提示信息" : info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    private int status;
    private String info;
    private String data;


    public <T> T getConvert(Class<T> tClass) {
        T convert = null;
        try {
            convert = JSON.parseObject(getData(), tClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convert;
    }

    public <T> List<T> getList(Class<T> tClass) {
        List<T> convert = null;
        try {
            convert = JSON.parseArray(getData(), tClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convert;
    }

}
