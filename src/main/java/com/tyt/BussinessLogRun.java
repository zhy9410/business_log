package com.tyt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author zhy
 * @since 2021/12/26 9:52
 */
@EnableAsync
@SpringBootApplication
public class BussinessLogRun {
    public static void main(String[] args) {
        SpringApplication.run(BussinessLogRun.class, args);
        System.out.println("项目启动");
    }
}
