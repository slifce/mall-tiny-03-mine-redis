package com.macro.mall.tiny.exception;

/**
 * Created by Administrator on 2020/7/29.
 */
public class ResultNotOneException extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = 4066956294068723693L;

    public ResultNotOneException() {
        super();
    }

    public ResultNotOneException(String message, Throwable cause, boolean enableSuppression,
                                 boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ResultNotOneException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResultNotOneException(String message) {
        super(message);
    }

    public ResultNotOneException(Throwable cause) {
        super(cause);
    }

    public static void main(String[] args) {
        System.out.println("2020.01.1-2025.01.05".replace('.', '-'));
    }
}

