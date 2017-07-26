package com.lxl.web.util;

import java.util.HashMap;

/*
  * @Description: TODO
  * @author: lxl
  * @date: 2017/7/26
  */
public class ResultValue extends HashMap<Object, Object> {

    public ResultValue() {
        super();
        this.setStatus(0);
    }

    public ResultValue(int status) {
        super();
        this.setStatus(status);
    }

    public void setStatus(int status) {
        this.put("status", status);
    }

    public void setMessage(Object message) {
        this.put("message", message);
    }

    public void setError(ErrorCode code) {
        this.put("code", Integer.parseInt(code.toString()));
    }

}
