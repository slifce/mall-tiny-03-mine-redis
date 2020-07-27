package com.macro.mall.tiny.common.utils;

import org.springframework.util.StringUtils;

/**
 * @ClassName: DataDesensitizationUtil
 * @Description: 数据脱敏工具类
 * Created by Administrator on 2020/7/24.
 */
public class DataDesensitizationUtil {

    /**
     * 隐藏身份证和电话号码等数字类型信息
     *
     * @param paramsStr
     * @return
     */
    public static String dealWithCode(String paramsStr){
        String returnStr = "";
        if(StringUtils.isEmpty(paramsStr)){
            return paramsStr;
        }
        if(paramsStr.length() <= 3){
            returnStr = paramsStr;
        }else if(paramsStr.length() == 8){
            returnStr = paramsStr.substring(0, 2)+"****"+paramsStr.substring(paramsStr.length()-2);
        }else if(paramsStr.length() == 9){
            returnStr = paramsStr.substring(0, 2)+"****"+paramsStr.substring(paramsStr.length()-3);
        }else if(paramsStr.length() == 11 || paramsStr.length() == 15 || paramsStr.length() == 18){
            returnStr = paramsStr.substring(0, 3)+"****"+paramsStr.substring(paramsStr.length()-4);
        }else {
            returnStr = paramsStr.substring(0, 3)+"****"+paramsStr.substring(paramsStr.length()-3);
        }
        return returnStr;
    }

    /**
     * 隐藏邮箱信息---“@”为分隔
     * @param email
     * @return
     */
    public static String maskEmail(String email) {
        String emailStr = "";
        if (StringUtils.isEmpty(email) || !email.contains("@")) {
            return email;
        }
        int beforeLength = email.lastIndexOf("@");
        if(beforeLength == 0){
            emailStr = email;
        }else if(beforeLength == 1 || beforeLength == 2){
            emailStr = email.substring(0, 1) + "***"+email.substring(email.lastIndexOf("@"));
        }else{
            int num = beforeLength-2;
            String str = "";
            for (int i = 0; i < num; i++) {
                str += "*";
            }
            emailStr = email.substring(0,2) + str + email.substring(email.lastIndexOf("@"));
        }
        return emailStr;
    }

    /**
     * 隐藏用户名信息
     * @param paramsStr
     * @return
     */
    public static String dealWithUserName(String paramsStr){
        String returnStr = "";
        if(StringUtils.isEmpty(paramsStr)){
            return paramsStr;
        }
        if(paramsStr.contains("@")){
            return maskEmail(paramsStr);
        }else{
            if(paramsStr.length() <= 3){
                returnStr = paramsStr;
            }else if(paramsStr.length() > 3 && paramsStr.length() <= 8){
                returnStr = paramsStr.substring(0, 1)+"****"+paramsStr.substring(paramsStr.length()-2);
            }else if(paramsStr.length() == 8){
                returnStr = paramsStr.substring(0, 2)+"****"+paramsStr.substring(paramsStr.length()-2);
            }else if(paramsStr.length() == 9){
                returnStr = paramsStr.substring(0, 2)+"****"+paramsStr.substring(paramsStr.length()-3);
            }else if(paramsStr.length() == 11 || paramsStr.length() == 15 || paramsStr.length() == 18){
                returnStr = paramsStr.substring(0, 3)+"****"+paramsStr.substring(paramsStr.length()-4);
            }else {
                returnStr = paramsStr.substring(0, 3)+"****"+paramsStr.substring(paramsStr.length()-3);
            }
        }
        return returnStr;
    }

}

