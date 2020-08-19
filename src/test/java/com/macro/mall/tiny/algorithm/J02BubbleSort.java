package com.macro.mall.tiny.algorithm;

/**
 * Created by Administrator on 2020/8/19.
 * 冒泡排序
 * 双层for循环遍历，
 * 外层次数为数组长度大小
 * 内层次数为数组长度大小减去当前外层数值再减1
 */
public class J02BubbleSort {

    public static void bubbleSort(int[] arr, int target){
        System.out.println("初始数据： ");
        for (int p = 0 ; p < arr.length;p++){
            System.out.print(arr[p]+"  ");
        }
        for(int i = 0; i < arr.length; i ++){
            for(int j = 0; j < arr.length - i - 1; j++){
                if(arr[j+1]<arr[j]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        System.out.println();
        System.out.println("排序后数据：");
        for (int q = 0 ; q < arr.length;q++){
            System.out.print(arr[q]+"  ");
        }
    }

    public static void main(String[] args) {
        int[] a = {1,9,6,5,15,13};
        int target = 11;
        bubbleSort(a, target);
    }

}
