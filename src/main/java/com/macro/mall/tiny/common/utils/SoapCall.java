package com.macro.mall.tiny.common.utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2020/8/10.
 */
public class SoapCall {

    /**
     * 保险网增减券方法
     * @param orderid 订单号
     * @param handtype 中民券处理方式（1增0减）
     * @param username 用户名
     * @param flcoupon 中民券数目
     * @param coupontype 中民券类型
     * @param fromtype 来源
     * @param memo 说明
     * @return 返回值说明:1操作成功,-1操作失败,-2签名错误，-3系统异常
     * @throws Exception
     */
    public static String updateCoupon(String orderid, int handtype, String username, BigDecimal flcoupon, int coupontype, int fromtype, String memo) throws Exception{
        //正式环境时用https://m.zm123.com/appwebservice/wineworldservice.asmx?WSDL
        //测试环境时用https://m2015.zm123.com/appwebservice/wineworldservice.asmx?WSDL
        URL wsUrl = new URL("https://m2015.zm123.com/appwebservice/wineworldservice.asmx?WSDL");

        HttpURLConnection conn = (HttpURLConnection)wsUrl.openConnection();

        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        OutputStream os = conn.getOutputStream();

        String sign = username + orderid + "10013" + "zm123_com_zmbx";
        String soap = "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">" +
                "<soap12:Body> <ZminOrderPayNotify xmlns=\"http://tempuri.org/\">" +
                "<orderid>"+orderid+"</orderid>" +
                "<handtype>"+handtype+"</handtype>" +
                "<username>"+username+"</username>" +
                "<flcoupon>"+flcoupon+"</flcoupon>" +
                "<coupontype>"+coupontype+"</coupontype>" +
                "<fromtype>"+fromtype+"</fromtype>" +
                "<sign>"+sign+"</sign>" +
                "<memo>"+memo+"</memo>" +
                "</ZminOrderPayNotify> </soap12:Body> </soap12:Envelope>";

        os.write(soap.getBytes());

        InputStream is = conn.getInputStream();

        byte[] b = new byte[1024];
        int len = 0;
        String s = "";
        while((len = is.read(b)) != -1){
            String ss = new String(b, 0, len, "UTF-8");
            s += ss;
        }
        String[] a = s.split("<ZminOrderPayNotifyResult>");
        String[] c = a[1].split("</ZminOrderPayNotifyResult>");

        is.close();
        os.close();
        conn.disconnect();

        return c[0];
    }

    //支付页面使用中民券
    public static void useCoupon(String orderid, String username, BigDecimal flcoupon, int coupontype, int fromtype, String memo) throws Exception{
        updateCoupon(orderid, 1, username, flcoupon, coupontype, fromtype, memo);
    }

}
