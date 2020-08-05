package com.macro.mall.tiny.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author lyp
 * @Date 2020/8/5 11:11
 * @Description 防重复提交验证
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckSubmitRepeat {


    /**
     * 重复提交提示语
     *
     * @return
     */
    String msg() default "请勿重复提交!";
}

