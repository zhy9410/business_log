package com.tyt.handler;

import java.util.Map;

/**
 * @author zhy
 * @since 2021/12/26 11:06
 */
public class BusinessLog {
    /**
     * 请求ip
     */
    private String requestIP;
    /**
     * 请求方式
     */
    private String uri;
    /**
     * 请求方式
     */
    private String method;
    /**
     * 请求参数
     */
    private Map<String, Object> params;
    /**
     * 返回结果是否成功 默认true
     */
    private boolean isSuccess = true;
    /**
     * 结果信息
     */
    private String resultInfo;


    public String getRequestIP() {
        return requestIP;
    }

    public void setRequestIP(String requestIP) {
        this.requestIP = requestIP;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getResultInfo() {
        return resultInfo;
    }

    public void setResultInfo(String resultInfo) {
        this.resultInfo = resultInfo;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "BusinessLog{" +
                "requestIP='" + requestIP + '\'' +
                ", uri='" + uri + '\'' +
                ", method='" + method + '\'' +
                ", params=" + params +
                ", isSuccess=" + isSuccess +
                ", resultInfo='" + resultInfo + '\'' +
                '}';
    }
}
