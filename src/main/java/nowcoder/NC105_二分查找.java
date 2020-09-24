package nowcoder;

import java.util.Arrays;
import java.util.Random;

/**
*********************************************************************
* 
* @author poles
* @date 2020/8/27 12:55 下午
*
*********************************************************************
*/
public class NC105_二分查找 {
    /**
     * 二分查找
     * @param n int整型 数组长度
     * @param v int整型 查找值
     * @param a int整型一维数组 有序数组
     * @return int整型
     */
    public static int upper_bound_ (int n, int v, int[] a) {

        //计算n的索引下标
        int indexN = getIndex(n, 0, a.length - 1, a);

        //计算v的索引下标
        int indexV = getIndex(v, 0, a.length - 1, a);

        return Math.min(indexN, indexV);
    }

    private static int getIndex(int num, int start, int end, int[] a){
        if(end - start == 1){
            if(a[start] >= num){
                return start + 1;
            }else if(a[end] >= num){
                return end + 1;
            }else{
                return a.length + 1;
            }
        }

        if(start >= end){
            return a[end] >= num ? end + 1 : a.length + 1;
        }

        int mid = (end - start + 1) / 2 + start;
        //优先判断小区间的，小区间里的数字可能正好也跟a[mid]相等呢
        if(num <= a[mid]){
            return getIndex(num, start, mid, a);
        }else{
            return getIndex(num, mid + 1, end, a);
        }
    }

    public static void main(String[] args) {
        int[] a = new int[100];
        Random r = new Random();
        for(int i = 0; i < 100; i++){
            a[i] = r.nextInt(99) + 1;
        }

        Arrays.sort(a);

        int n = r.nextInt(110) + 1;
        int v = r.nextInt(110) + 1;


//        int n = 100;
//        int v = 1;
//        int a[] = {2,3,4,4,4,7,7,8,10,10,11,12,13,14,15,15,17,18,19,23,24,24,24,24,25,26,26,26,27,27,28,29,29,30,33,36,38,38,40,40,41,43,43,43,44,46,46,47,51,52,52,53,54,56,57,57,57,58,58,61,61,61,62,64,64,66,66,67,67,67,70,72,74,74,74,75,75,78,78,78,79,79,80,83,83,83,83,84,84,86,88,89,89,90,91,91,92,93,93,96};


        System.out.println("n=" + n);
        System.out.println("v=" + v);
        System.out.println("a[] = " + Arrays.toString(a));
        int result = upper_bound_(n,v,a);
        System.out.println("查询结果为：" + result);
    }
}
