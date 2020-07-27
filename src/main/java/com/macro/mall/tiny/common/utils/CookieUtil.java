package com.macro.mall.tiny.common.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: CookieUtil
 * @Description: cookie工具类
 * Created by Administrator on 2020/7/24.
 */
public class CookieUtil {
    public static final long EXPIRE_TIME=10800L;

    public static final long SLIDER_EXPIRE_TIME=180;
    /**
     *
     * @Title: addCookie
     * @Description: 添加cookie
     * @param :	response, name ,value ,maxAge
     * @return: 1--添加cookie成功 ； 0 -- 添加cookie失败
     */
    public static Integer addCookie(HttpServletRequest request,HttpServletResponse response , String name , String value , long maxAge){
        try {
            String path = request.getContextPath();
            Cookie cookie = new Cookie(name , value);
            cookie.setPath("/");
            if(maxAge > 0){
                cookie.setMaxAge((int) maxAge);
            }
            response.addCookie(cookie);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     *
     * @Title: getCookie
     * @Description: 根据name获取相对应的从cookie
     * @param :	request , name
     * @return: Cookie
     */
    public static Cookie getCookie(HttpServletRequest request , String name ){
        Map<String ,Cookie> cookieMap = parseRequestCookie(request);
        Cookie cookie = null;
        if(cookieMap!=null){
            if(cookieMap.containsKey(name)){
                cookie = cookieMap.get(name);
            }
        }
        return cookie;
    }

    /**
     *
     * @Title: parseRequestCookie
     * @Description: 封装request中cookie到map中
     * @param :	request
     * @return: Map
     */
    private static Map parseRequestCookie(HttpServletRequest request){
        Map<String ,Cookie> cookieMap = new HashMap();
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            if(cookies.length>0){
                for(Cookie cookie : cookies){
                    cookieMap.put(cookie.getName(), cookie);
                }
            }
        }
        return cookieMap;
    }
    /**
     *
     * @Title: removeCookie
     * @Description:删除cookie
     * @param request
     * @param res
     * @param name
     */
    public static void removeCookie(HttpServletRequest request ,HttpServletResponse res,String name){
        Cookie cookie= new Cookie(name , null);
        //String path = request.getContextPath();
        cookie.setPath("/");
        cookie.setMaxAge(0);
        res.addCookie(cookie);
    }

    public static void removeCookies(HttpServletRequest request ,HttpServletResponse response){
        Cookie[] cookies=request.getCookies();
        String path = request.getContextPath();
        for(Cookie cookie : cookies){
            cookie.setMaxAge(0);
            cookie.setPath(path+"/");
            response.addCookie(cookie);
        }
    }
}

