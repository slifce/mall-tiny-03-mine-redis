package com.macro.mall.tiny.beans;

import com.macro.mall.tiny.mbg.model.PmsBrand;
import org.springframework.beans.BeanUtils;

import java.beans.Beans;

/**
 * Created by Administrator on 2020/7/28.
 */
public class BeansCopy {

    public static void beansCopy(){
        PmsBrand pmsBrand1 = new PmsBrand();
        pmsBrand1.setName("产品1");
        pmsBrand1.setLogo("login1");
        System.out.println(pmsBrand1.toString());
        PmsBrand pmsBrand2 = new PmsBrand();
        BeanUtils.copyProperties(pmsBrand1,pmsBrand2);
        System.out.println(pmsBrand2.toString());
    }

    public static void main(String[] args) {
        beansCopy();
    }

}
