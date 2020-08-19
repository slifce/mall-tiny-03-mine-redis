package com.macro.mall.tiny.algorithm;

/**
 * Created by Administrator on 2020/8/19.
 * 二分查找
 * 又叫折半查找，要求待查找的序列有序。每次取中间位置的值与待查关键字比较，
 * 如果中间位置的值比待查关键字大，则在前半部分循环这个查找的过程，
 * 如果中间位置的值比待查关键字小，则在后半部分循环这个查找的过程。
 * 知道查找到了为止，否则序列中没有待查的关键字。
 */
public class J01BiSearch {

    public static int bidSearch(int[] arr, int target){
        int lo = 0;
        int hi = arr.length -1;
        int mid;
        while (lo <= hi){
            mid = (lo + hi)/2;//中间位置
            if (arr[mid] == target){
                return mid+1;
            }else if(arr[mid] < target){//向右查找
                lo = mid + 1;
            }else if(arr[mid] > target){//向左查找
                hi = mid -1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = {1,3,6,9,11,15};
        int target = 11;
        int result = bidSearch(a, target);
        System.out.println(result);
    }

}
