package nowcoder;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
*********************************************************************
* 
* @author poles
* @date 2020/8/28 9:54 下午
*
*********************************************************************
*/
public class 最长无重复子串长度 {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 5, 6, 2, 4, 5, 6, 7,  2, 2,   2,  1,  2,  3,  4,  5,  6,  7,  8,  9};
                  // 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,  20, 21
        System.out.println(maxLength(arr));
    }
    /**
     *
     * @param arr int整型一维数组 the array
     * @return int整型
     */
    public static int maxLength (int[] arr) {
        if(arr == null){
            return 0;
        }

        //方法一：
//        int max = 0;
//        //存放各个数字的索引位置
//        HashMap<Integer, Integer> map = new HashMap<>();
//
//        //起始指针，每次如果发现有重复数字，则i指向重复数字中的第一个重复数字的下一个位置
//        int i = 0;
//        int j = 0;
//        for(; j < arr.length; j++){
//            Integer index = map.get(arr[j]);
//            //索引小于i的数字就安息吧，就当不存在了
//            if(index != null && index >= i){
//                max = Math.max(max, (j - i));
//                i = index + 1;  //当发现有重复数字时，那么取出重复数字的位置，i从重复数字的下一个位置开始计数
//            }
//            map.put(arr[j], j);  //如果是重复数字，则也要覆盖索引值
//        }
//
//        //如果中途一溜烟全走完了，一个重复的都没有，你都来不及判断重复的，就结束了，所以需要最后补加一下这种特殊情况
//        max = Math.max(max, (j - i));
//
//        return max;


        //方法二：
//        int left = 0, right = 0;
//        Set<Integer> set = new HashSet<>();
//        int res = 1;
//        while(right < arr.length){
//            if(!set.contains(arr[right])){
//                set.add(arr[right]);
//                right++;
//            }else{
//                set.remove(arr[left]);
//                left++;
//            }
//            res = Math.max(res, set.size());
//        }
//        return res;

        //方法二优化：未实现，可以提供一个思路，就是不要做set.remove, 而是做set.container判断时，增加一个条件，就是小于left的索引的那些元素不参与contains比较，节省一次remove操作，可能这样的话就只能用hashMap来实现了吧，或者用Set, 但是set里的对象既要记录值，又要记录索引值
        int left = 0, right = 0;
        Set<Integer> set = new HashSet<>();
        int res = 1;
        while(right < arr.length){
            if(!set.contains(arr[right])){
                set.add(arr[right]);
                right++;
            }else{
                //set.remove(arr[left]);  //这一句可以不要，然后寄一个判断，就是
                left++;
            }
            res = Math.max(res, set.size());
        }
        return res;
    }
}
