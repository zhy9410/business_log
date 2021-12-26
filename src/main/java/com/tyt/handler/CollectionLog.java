package com.tyt.handler;

import java.lang.annotation.*;

/**
 * @author zhy
 * @since 2021/12/26 10:14
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface CollectionLog {
    /**
     * 参数位置
     * @return
     */
    BusinessLogEnum.ParamPosition paramPosition() ;
    /**
     * 获取参数属性
     * @return
     */
    String[] paramFields() default {};

    /**
     * 结果字段
     * @return
     */
    String resultField() default "";
    /**
     * 结果信息字段
     * @return
     */
    String infoField() default "info";
    /**
     * 请求成功标志
     * @return
     */
    String sucessSign() default "";
}
