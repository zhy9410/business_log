package com.tyt.handler;

/**
 * @author zhy
 * @since 2021/12/26 12:13
 */
public interface BusinessLogEnum {
    // 主键位置

    enum ParamPosition {
        // 无参
        NULL,
        // 主键在地址中 暂时获取一个参数
        URI,
        // 主键在参数中
        PARAM;
    }

}
