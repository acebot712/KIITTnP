package com.example.asterdan712.kiittnp;

/**
 * Created by asterdan712 on 9/1/2017.
 */

public class CustomObject {

    public String noticeURL = "";
    public String noticeDesc = "";

    public CustomObject(String noticeURL, String noticeDesc) {
        this.noticeURL = noticeURL;
        this.noticeDesc = noticeDesc;
    }

    public CustomObject() {

    }

    public String getNoticeURL() {
        return noticeURL;
    }

    public String getNoticeDesc() {
        return noticeDesc;
    }

    public void setNoticeURL(String noticeURL) {
        this.noticeURL = noticeURL;
    }

    public void setNoticeDesc(String noticeDesc) {
        this.noticeDesc = noticeDesc;
    }

    @Override
    public String toString() {
        return "CustomObject{" +
                "noticeURL='" + noticeURL + '\'' +
                ", noticeDesc='" + noticeDesc + '\'' +
                '}';
    }
}
