package com.example.contentproviderduanxinbeifeng;

public class SmsInfo {
    private String address;//发送地址
    private long date;//发送时间
    private int type;//类型
    private String body;//内容
    private int id;

    //构造方法
    public SmsInfo(String address, long date, int type, String body) {
        this.address = address;
        this.date = date;
        this.type = type;
        this.body = body;
    }
    public int getId(){
        return this.id;
    }

    public String getBody(){
        return this.body;
    }

    public String getAddress(){
        return  this.address;
    }

    public int getType(){
        return this.type;
    }

    public Long getDate(){
        return this.date;
    }

}
