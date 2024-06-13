package com.zyj.common;

import java.util.List;

public class DataJson<E> {

    private Integer code;

    private String message;

    private List<E> data;

    private Integer count;

    public DataJson() {
        super();
        // TODO Auto-generated constructor stub
    }

    public DataJson(Integer code, String message, List<E> data, Integer count) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
        this.count = count;
    }

    public DataJson(Integer code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<E> getData() {
        return data;
    }

    public void setData(List<E> data) {
        this.data = data;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }


}
