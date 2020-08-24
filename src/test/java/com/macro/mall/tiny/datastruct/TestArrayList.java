package com.macro.mall.tiny.datastruct;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/8/24.
 */
public class TestArrayList {
    public static void main(String[] args) {
        List<Object> objects = new ArrayList<Object>();
        for(int i = 0; i < 12; i++){
            if(i>10){
                objects.add(i);
            }else{
                objects.add("0"+i);
            }
        }
    }
}
