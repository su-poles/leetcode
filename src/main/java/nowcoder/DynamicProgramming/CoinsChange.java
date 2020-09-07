package nowcoder.DynamicProgramming;
/**
*********************************************************************
* 
* @author poles
* @date 2020/8/30 5:36 下午
* 最值型问题：
 * 2、5、7三种面值的硬币，恰好支付27元的商品，最少需要几枚硬币
 * 引申，有n中面值的硬币，恰好需要支付A元的商品，最少需要k枚硬币，求K
*********************************************************************
*/
public class CoinsChange {
    public static void main(String[] args) {
        CoinsChange cc = new CoinsChange();
        //面额
        int[] coins = {2,5,7};
        int A = 23;
        for(int T = 0; T < 100000; T++){
            int result = cc.change(coins, T);
            if(result == Integer.MAX_VALUE){
                System.out.println("组成【" +T+"】的最少数量为：" + result);
            }
        }
    }

    /**
     * 使用动态规划解题：
     * f[A] = minx{f[A-coins[0]] - Ak, f[A-coins[1] - Ak, ... f[A-coins[coins.length-1] - Ak}
     * @author liyanlong
     * @date 2020-08-30 17:40:53
     * @param coins 硬币种类
     * @param A 需要支付A元
     * @return int
     */
    public int change(int[] coins, int A){
        //从小到大，先求f(1), 再求f(2), 再求f(3) .... 最后求f(27)

        //初始化一个数组，存储f(1), f(2)等的结果，包括f(0)
        int[] result = new int[A + 1];
        result[0] = 0;  //边界值
        for(int i = 1; i <= A; i++){
            result[i] = Integer.MAX_VALUE;   //初始化result[i], 无穷大表示无法表示，比如1元，就用2、5、7三种面额的硬币无法表示

            for (int deno : coins) {        //deno: denomination, 面额

                //判断边界，小心溢出，如果溢出，则忽略即可
                if (i - deno < 0) {
                    //如果int coins是有序的，从小打到，就用break, 如果不确定就用continue
                    continue;
                }

                //如果用到了负的索引，则直接忽略，这部分需要看《动态规划笔记.md》中的f[x]计算的那部分
                if (result[i - deno] == Integer.MAX_VALUE) {
                    continue;
                }

                //f[x] = min{f[x-2] + 1, f[x-5] + 1, f[x-7] + 1}
                result[i] = Math.min(result[i], result[i - deno] + 1);
            }
        }

        return result[A];
    }
}
