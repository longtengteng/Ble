package com.clj.blesample.model;

import android.text.TextUtils;

/**
 * Copyright (c) 山东六牛网络科技有限公司 https://liuniukeji.com
 *
 * @Description
 * @Author zhugefubin tel:553629767;
 * @Copyright Copyright (c) 山东六牛网络科技有限公司 保留所有版权(https://www.liuniukeji.com)
 * @Date 2018-08-07 14:12
 * @CreateBy Android Studio
 * @ModifiedBy
 */

public class UserinfoModel {

    /**
     * user_id : 65//类型：String  必有字段  备注：用户id
     * user_name : 15253995383//类型：String  必有字段  备注：用户名
     * mobile : 15253995383 //类型：String  必有字段  备注：手机号
     * email : mixed//类型：Mixed  必有字段  备注：邮箱
     * head_pic : mixed   //类型：Mixed  必有字段  备注：头像
     * disabled : 1//类型：String  必有字段  备注：用户状态 0禁用 1正常
     * nickname : mixed  //类型：Mixed  必有字段  备注：昵称
     * sex : 2//类型：String  必有字段  备注：性别 1 男 2 女
     * birthday : 0 //类型：String  必有字段  备注：生日
     * rank_id : 1 //类型：String  必有字段  备注：用户级别 1.普通会员 2.铜牌 3.银牌 4.金牌
     * weixin : mixed//类型：Mixed  必有字段  备注：微信
     * weibo : mock //类型：String  必有字段  备注：微博
     * pro_id : 0//类型：String  必有字段  备注：省id
     * city_id : 0//类型：String  必有字段  备注：市id
     * county_id : 0//类型：String  必有字段  备注：县id
     * token : 421951017421153734//类型：String  必有字段  备注：用户token
     * province : mixed//类型：Mixed  必有字段  备注：省
     * city : mixed//类型：Mixed  必有字段  备注：市
     * county : mixed//类型：Mixed  必有字段  备注：县
     * user_no : mock//类型：String  必有字段  备注：邀请码
     * signature : mock//类型：String  必有字段  备注：签名
     * pay_password :  //类型：String  必有字段  备注：是否设置了支付密码 1.是 0.否
     * user_money :  //类型：String  必有字段  备注：账户余额
     * recharge_money :  //类型：String  必有字段  备注：充值金额
     */


    private String user_id;
    private String user_name;
    private String mobile;
    private String email;
    private String head_pic;
    private int disabled;
    private String nickname;
    private String sex;
    private String birthday;
    private int rank_id;
    private String weixin;
    private String weibo;
    private String pro_id;
    private String city_id;
    private String county_id;
    private String token;
    private String province = "";
    private String city = "";
    private String county = "";
    private String user_no;
    private String signature;
    private String user_money;
    private String recharge_money;
    private int verify_state;
    private int pay_password = 0;
    /**
     * disabled : 1
     * rank_id : 1
     * parent_id : 68
     * user_qr_code :
     * parent_no : 813601
     */

    private String parent_id;
    private String user_qr_code;
    private String parent_no;

    public String getUser_money() {
        return user_money;
    }

    public void setUser_money(String user_money) {
        this.user_money = user_money;
    }

    public String getRecharge_money() {
        return recharge_money;
    }

    public void setRecharge_money(String recharge_money) {
        this.recharge_money = recharge_money;
    }

    public int getPay_password() {
        return pay_password;
    }

    public void setPay_password(int pay_password) {
        this.pay_password = pay_password;
    }

    /**
     * rank_id : 1//类型：String  必有字段  备注：用户级别 1.普通会员 2.铜牌 3.银牌 4.金牌
     * verify_state : 0//类型：String  必有字段  备注：实名认证状态：0待审核 1拒绝 2通过
     */

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSignature() {
        return signature;
    }


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHead_pic() {
        return head_pic;
    }

    public void setHead_pic(String head_pic) {
        this.head_pic = head_pic;
    }

    public int getDisabled() {
        return disabled;
    }

    public void setDisabled(int disabled) {
        this.disabled = disabled;
    }

    public String getNickname() {
        return TextUtils.isEmpty(nickname) ? user_name : nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getRank_id() {
        return rank_id;
    }

    public void setRank_id(int rank_id) {
        this.rank_id = rank_id;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getWeibo() {
        return weibo;
    }

    public void setWeibo(String weibo) {
        this.weibo = weibo;
    }

    public String getPro_id() {
        return pro_id;
    }

    public void setPro_id(String pro_id) {
        this.pro_id = pro_id;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getCounty_id() {
        return county_id;
    }

    public void setCounty_id(String county_id) {
        this.county_id = county_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getUser_no() {
        return user_no;
    }

    public void setUser_no(String user_no) {
        this.user_no = user_no;
    }

    public int getVerify_state() {
        return verify_state;
    }

    public void setVerify_state(int verify_state) {
        this.verify_state = verify_state;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getUser_qr_code() {
        return user_qr_code;
    }

    public void setUser_qr_code(String user_qr_code) {
        this.user_qr_code = user_qr_code;
    }

    public String getParent_no() {
        return parent_no;
    }

    public void setParent_no(String parent_no) {
        this.parent_no = parent_no;
    }
}
