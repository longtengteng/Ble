package com.clj.blesample.base.z.helper;

/**
 * Copyright (c) 山东六牛网络科技有限公司 https://liuniukeji.com
 *
 * @Description
 * @Author zhugefubin tel:553629767;
 * @Copyright Copyright (c) 山东六牛网络科技有限公司 保留所有版权(https://www.liuniukeji.com)
 * @Date 2018-07-07 10:29
 * @CreateBy Android Studio
 * @ModifiedBy
 */

public class TimeHelper {
    /**
     * 秒转换成 1天2小时3分4秒的形式
     *
     * @param second 秒
     * @return
     */
    public static String second2String(long second) {
        String strPass="";
        if (second<=0){
            second=-second;
            strPass="已过";
        }else {
            strPass="还剩";
        }
        long day = second / 86400;
        long hour = second % 86400 / 3600;
        long minute = second % 86400 % 3600 / 60;
        long second2 = second % 86400 % 3600 % 60;
        return strPass+ String.format(" %s天%s小时%s分%s秒", day == second ? 0 : day, hour == second ? 0 : hour, minute == second ? 0 : minute, second2);
    }
}
