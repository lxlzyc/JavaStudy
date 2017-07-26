package com.lxl.web.util;

/*
  * @Description: TODO
  * @author: lxl
  * @date: 2017/7/26
  */
public enum ErrorCode {
    ERROR(1),
    SERVER_NOTFOUND(2);
    private int code;

    private ErrorCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return String.valueOf(this.code);
    }
}
