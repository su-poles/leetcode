package nowcoder.DynamicProgramming;
/**
*********************************************************************
* 
* @author poles
* @date 2020/8/30 6:16 下午
* 计数型的动态规划：
* 给定m行n列的网格，有一个机器人从左上角（0，0）出发，每一步只能向右或者向下走1格，问：有多少种不同的方式走到右下角
 *
 * 分析：  加法原理 （无重复、无遗漏）
 * f(m,n) = f(m-1, n) + f(m, n-1)
 * 如果: f(m-1) = X,  f(m, n-1) = Y
 * 则：f(m,n) = X + Y
*********************************************************************
*/
public class UniquePaths {
    public static void main(String[] args) {
        int m = 1;
        int n = 3;
        System.out.println(new UniquePaths().path(m, n));
    }

    private int path(int m, int n){
        //计算，从[0,0] 到 [m,n] 一共有多少种情况即可
        //转移方程f[m,n] = f[m-1,n] + f[m,n-1], 简单吧
        //比如[0,1] 只能是从[0,0]向右移动1位，所以f[0,1]=1
        int[][] f = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                //判断一下边界条件
                if(i == 0 || j == 0){
                    f[i][j] = 1;
                }else{
                    f[i][j] = f[i-1][j] + f[i][j - 1];
                }
            }
        }

        return f[m-1][n-1];
    }
}
