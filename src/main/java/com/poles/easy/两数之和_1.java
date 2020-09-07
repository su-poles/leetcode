package com.poles.easy;

import java.util.*;

/**
*********************************************************************
*
* @author poles
* @date 2020/8/22 10:09 上午
* 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
*
* 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
*
* 示例:
*
* 给定 nums = [2, 7, 11, 15], target = 9
*
* 因为 nums[0] + nums[1] = 2 + 7 = 9
* 所以返回 [0, 1]
*
* Related Topics 数组 哈希表
*********************************************************************
*/
public class 两数之和_1 {
    public static void main(String[] args) {
        //1. 考虑正数和负数，需要两个数组
        //2. 考虑数字重复，比如有两组，【2，7】
        //3. 元素数量可控

        int N = 30;
        int[] ary = new int[N];

        Random r = new Random();
        for(int i = 0; i < N; i++){
            ary[i] = r.nextInt(10);
        }
        System.out.println("原始数组：");
        for(int i = 0; i < ary.length; i++){
            System.out.println(i + "=>" + ary[i] + ", ");
        }

        int target = 10;
        System.out.println(twoNums(ary, target));
    }

    public static ArrayList<String> twoNums(int[] nums, int target){
        ArrayList<String> result = new ArrayList<>();

        //存放元素的位置
        Map<Integer, LinkedList<Integer>> postion = new HashMap<>();
        for(int index = 0; index < nums.length; index++){
            int element = nums[index];
            //如果包含负数，则该条件需要去掉
//            if(element > target){
//                continue;
//            }

            int another = target - element;
            LinkedList<Integer> p = postion.get(another);
            if(p != null){
                Integer anotherIndex = p.pollFirst();
                if(anotherIndex != null){
                    result.add(anotherIndex + ":" + index);
                }
                postion.put(another, p);
            }

            LinkedList<Integer> val = postion.get(element);
            if(val == null){
                val = new LinkedList<>();
            }
            val.add(index);
            postion.put(element, val);
        }

        return result;
    }
}
