package com.macro.mall.tiny.exception;

public class CheckSubmitRepeatException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    // 提供无参数的构造方法
    public CheckSubmitRepeatException() {
    }

    // 提供一个有参数的构造方法，可自动生成
    public CheckSubmitRepeatException(String msg) {
        super(msg);// 把参数传递给Throwable的带String参数的构造方法
    }

}

