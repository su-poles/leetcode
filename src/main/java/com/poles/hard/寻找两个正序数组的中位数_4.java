package com.poles.hard;
/**
*********************************************************************
* 
* @author poles
* @date 2020/11/15 8:52 下午
*
*********************************************************************
*/
public class 寻找两个正序数组的中位数_4 {
    public static void main(String[] args) {
        int[] nums1 = {2};
        int[] nums2 = {};

        寻找两个正序数组的中位数_4 t = new 寻找两个正序数组的中位数_4();
        System.out.println(t.findMedianSortedArrays(nums1, nums2));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int i = 0;
        int j = 0;
        int k = 0;
        int len = nums1.length + nums2.length;
        int[] mergeArray = new int[len];
        while(i < nums1.length && j < nums2.length){
            if(nums1[i] <= nums2[j]){
                mergeArray[k++] = nums1[i++];
            }else{
                mergeArray[k++] = nums2[j++];
            }
        }

        while (i < nums1.length) {
            mergeArray[k++] = nums1[i++];
        }

        while (j < nums2.length) {
            mergeArray[k++] = nums2[j++];
        }

        if(len % 2 == 0){
            int secondIndex = len / 2;
            int firstIndex = secondIndex - 1;
            return (mergeArray[firstIndex] + mergeArray[secondIndex])  / 2.0;
        }else{
            return mergeArray[len / 2];
        }
    }
}
