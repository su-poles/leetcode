package nowcoder.fastPower;
/**
*********************************************************************
* 
* @author poles
* @date 2020/8/29 9:10 下午
*
 * 求2^100 % 1000的结果
*********************************************************************
*/
public class 快速幂 {
    public static void main(String[] args) {
        int a = 2, b = 2000000013;  //求 a^b % 1000
        普通循环法(a, b);

        快次幂法(a, b);
    }

    public static void 快次幂法(int base, int power) {
        //2 ^ 100 % 1000 = (2 % 1000 * 2 % 1000 * 2 % 1000  * ...... * 2) % 1000
        //2 ^ 100 % 1000 = 4 ^ 50 % 1000 = (4 % 1000)^50 = (16 % 1000)^25 = (256 % 1000)^12 * 16 % 1000
        /**
         *  等式：a*b*c % 1000 =[(a * 1000) * (b % 1000) * (c % 1000)] % 1000， 这个应该没问题吧
         *  那么a^100 % 1000 是不是等于 (a % 1000)^1000 % 1000
         *  所以，【计算出最终数值取模】 和 【某乘式取模再乘以某乘式取模，然后积取模，再乘以另外的某模式取模，乘积再取模... 最终取模】值是一样的
         *
         *  另外还有一种取巧的办法，对于power而言，每次缩小一半会时不时的遇见各种奇数
         *  然后这个power就可以分解为：2^0 + 2^1 + 2^2 + 2^3 + 2^4 + 2^5 + 2^6 + 2^7 + .... 这种格式
         *  比如： 13 = 1 + 4 + 8， 那么5^13 = 5^1 * 5^4 * 5^8,   13 = 1101 = (8,4,2,1) 这种格式
         */
        long start = System.currentTimeMillis();
        int lastRemainder =  1;
        while(power > 0){
//            if(power % 2 == 1){     //奇数的最后一位肯定是1，偶数最后一位肯定是0，所以直接跟1进行按位与，结果为1表示奇数，否则就是偶数
            if((power & 1) == 1){     //Java中关系运算符优先级大于逻辑运算符，所以==的优先级高于&，一定要注意加括号，否则会报错
                //奇数
                lastRemainder = base * lastRemainder % 1000;
            }

//            power = power / 2;
//            power = power >> 2;       //代替： power = power /2
            power >>= 2;                //代替： power >>= 2
            //偶数 与 奇数，base都要翻倍
            base = base * base % 1000;
        }

        int finalRemainder = lastRemainder % 1000;
        System.out.println("快次幂法：最后的余数为：" + finalRemainder + ", 耗时：" + (System.currentTimeMillis() - start) + "毫秒");
    }

    public static void 普通循环法(int a, int b) {
            //        int b = 2000000000;  //20亿
            //2 ^ 100 % 1000 = (2 % 1000 * 2 % 1000 * 2 % 1000  * ...... * 2) % 1000
            long start = System.currentTimeMillis();
            int lastRemainder =  -1;
            for(int i = 0; i < b; i++){
                if(lastRemainder == -1){
                    //记录第一个余数
                    lastRemainder = a % 1000;
                }else{
                    //每个余数相乘然后 % 1000
                    lastRemainder = lastRemainder * a % 1000;
                }
            }

            int finalRemainder = lastRemainder % 1000;
            System.out.println("普通循环法：最后的余数为：" + finalRemainder + ", 耗时：" + (System.currentTimeMillis() - start) + "毫秒");
        }
    }
