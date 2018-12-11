package com.clj.blesample.ui.history;

public class HistoryBean {

    /**
     * header_pic : /Uploads/Picture/User/10/20171113_144106_15105552666192_9303.jpg
     * name : cccc
     * baby_id : 0
     * mac_address : 10
     * temp_num : 37
     * power_num : 0
     * time_str : 2018-12-07 18:31:35
     */

    private String header_pic;
    private String name;
    private int baby_id;
    private String mac_address;
    private int temp_num;
    private int power_num;
    private String time_str;

    public String getHeader_pic() {
        return header_pic;
    }

    public void setHeader_pic(String header_pic) {
        this.header_pic = header_pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBaby_id() {
        return baby_id;
    }

    public void setBaby_id(int baby_id) {
        this.baby_id = baby_id;
    }

    public String getMac_address() {
        return mac_address;
    }

    public void setMac_address(String mac_address) {
        this.mac_address = mac_address;
    }

    public int getTemp_num() {
        return temp_num;
    }

    public void setTemp_num(int temp_num) {
        this.temp_num = temp_num;
    }

    public int getPower_num() {
        return power_num;
    }

    public void setPower_num(int power_num) {
        this.power_num = power_num;
    }

    public String getTime_str() {
        return time_str;
    }

    public void setTime_str(String time_str) {
        this.time_str = time_str;
    }
}
