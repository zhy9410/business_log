package com.tyt.entiy;

/**
 * @author zhy
 * @since 2021/12/26 12:48
 */
public class R<T> {
    private int code;
    private String info;
    private T data;
    public static <T> R<T> success(String info, T data){
        return new R(200, info, data);
    }
    public static <T> R<T> error(int code, String info){
        return new R(code, info, null);
    }
    public R(int code, String info, T data) {
        this.code = code;
        this.info = info;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
