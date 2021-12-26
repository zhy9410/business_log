package com.tyt.handler;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author zhy
 * @since 2021/12/26 11:06
 */
@Component
public class BusinessLogService {
    @Async
    public void deal(BusinessLog businessLog){
        // 异步处理拿到的业务日志数据
        System.out.println("获取到的参数");
        System.out.println(businessLog);
    }
}
