package nowcoder.DynamicProgramming;
/**
*********************************************************************
* 
* @author poles
* @date 2020/8/30 8:42 下午
* 可行性型动态规划问题：
 * 给定一个数组a，里面记录了在第i个石头上，青蛙最多可以向右跳跃a[i]的距离
 * 有n块石头分别在x轴的0，1，2, ... , n-1位置(n为数组a的长度)，一只青蛙在石头0，想跳到石头n-1, 如果青蛙在第i块石头上，它最多可以向右跳距离a[i],
 * 问：青蛙能否跳到石头n-1
 *
 *
 * 分析：
 * 一、确定状态，明确子问题
 * 1. 青蛙可以跳到n-1, 那么这一步是从n-1坐标的某个石头i上跳过来的，i < n-1
 * 2. 那么首先需要满足两个条件，条件一：青蛙可以跳到石头i上；条件二：最后一步不能超过最大跳跃距离：n-1-i <= a[i]
 * 子问题：
 * 青蛙到底能不能跳到石头i (i < n-1)
 * 状态：设f[j]表示青蛙能不能跳到石头j
 * 二、转移方程
 * f[j] = OR_0<=i<j{f[i] AND i+a[i]>=j}
 *
 * 三、初始条件和边界情况
 * f[0] = True, 青蛙一开始就是第0块石头上，所以就是True
 * 没有边界情况，这里不会存在越界的情况
 *
 * 四、计算顺序
 * 从做到右，从小到大
 *
 * 时间复杂度O(N²）， 空间复杂度为数组大小：O(N)
 *
 *
 * 注意：本题也有个贪心算法可解，The time complexity of Greedy method is O(n), The time complexity of Dynamic Programming method is O(n²)
*********************************************************************
*/
public class JumpGame {
    public static void main(String[] args) {
        int[] a = {3,2,1,0,4};
//        int[] a = {2,3,1,1,4};
        System.out.println(new JumpGame().canJump(a));
    }

    /**
     * a为每块石头对应的最大跳跃距离
     * @author liyanlong
     * @date 2020-08-30 21:21:51
     * @param a 每个石头对应最大跳跃距离
     * @return boolean
     */
    private boolean canJump(int[] a){
        boolean[] f = new boolean[a.length];
        f[0] = true;
        for(int i = 1; i < a.length; i++){
            f[i] = false;  //默认不能到达

            //每次考虑能不能到i时，要看看i左边的石头能不能到达，对于能到达的石头看看距离够不够
            for(int j = 0; j < i; j++){
                //左边的某个石头能到到达时，看看距离够不够
//                f[i] = f[i] || f[j] && a[j] >= i - j;
//                if(f[i]){
//                    break;
//                }

                //从上面的式子中优化一点，只要找到任意一组为true就可以了，不需要全部做一个or操作
                if(f[j] && a[j] >= i - j){

                    f[i] = true;
                    break;
                }
            }
        }

        return f[a.length - 1];
    }

    /*
     * 时间复杂度：因为是双重循环，所以是O(n²), 这样说也对
     * 实际上是，第一次循环是1，第二次循环是2，第三次是3，所以应该是1+2+3+4+5...+n， 根据等差数列求和公式得：Sn=n*a1+n(n-1)d/2或Sn=n(a1+an)/2
     * 1+2+3+4+5...+n = n(1+n)/2 , 那就是：1/2 * n² + n,  所以时间复杂度就是O(n²)
     * 空间复杂度，仅仅是开辟了一个新的数组而已
     */
}
