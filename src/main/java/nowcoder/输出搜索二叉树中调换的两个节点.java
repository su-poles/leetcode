package nowcoder;

import nowcoder.domain.TreeNode;
import nowcoder.util.TreeUtil;

import java.util.ArrayList;
import java.util.Arrays;

/**
*********************************************************************
* 
* @author poles
* @date 2020/8/29 1:05 下午
*
*********************************************************************
*/
public class 输出搜索二叉树中调换的两个节点 {
    private ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        String input = "{34,20,38,3,29,35,44,2,4,24,30,#,37,42,45,1,#,#,12,23,26,#,32,36,#,40,21,#,#,#,#,5,15,43,#,25,28,31,33,#,#,39,41,#,#,#,8,14,18,#,22,#,#,27,#,#,#,#,#,#,#,#,#,6,10,13,#,16,19,#,#,#,#,#,7,9,11,#,#,#,17}";
        //还原成树
        TreeNode root = TreeUtil.buildOriginal(input);
        System.out.println(root.inOrderToString());

        输出搜索二叉树中调换的两个节点 a = new 输出搜索二叉树中调换的两个节点();
        System.out.println(Arrays.toString(a.findError(root)));
    }

    /**
     *
     * @param root TreeNode类 the root
     * @return int整型一维数组
     */
    public int[] findError (TreeNode root) {
        //1. 原本是个二叉搜索树，前序遍历就对了
        //2. 但是，其中两个数字调换了，任何位置都有可能
        //3. 所以，对前序遍历的结果中，找到这两个特殊的值就可以了：
        //4. 从开始遍历，找到第一个比后面大的数，肯定就是比较大的那个数， 找到第一个比前一个小的数字，就是那个比较小的数字
        //5. 有一种特殊情况，就是相邻的两个数字调换，先找第一个比较大的数字，然后如果遍历完了还招不到比较小的那个数字，那肯定就是第一个数字后面的那个数字了
        //6. 开始动手写代码

        inOrder(root);
        if(list.size() < 2){
            return null;
        }

        Integer big = null;
        Integer small = null;
        Integer small_maybe = null;
        for(int i = 0; i < list.size() - 1; i++){

            //找第一个大数
            if(big == null && list.get(i) > list.get(i+1)){
                big = list.get(i);
                small_maybe = list.get(i+1);
                continue;   //这个continue特别坑，一定要加上， 否则你想啊，这里的big已经找到了，后面的那个数肯定小于big，则下面的判断也会成功，直接small就成了big后面的那个数了，small还没找呢，就结束了，这是不对的
            }

            //找第二个小数, 此时的big已经找到， 找到small之后，直接退出循环即可
            if(big != null && list.get(i) > list.get(i+1)){
                small = list.get(i+1);
                break;
            }
        }

        if(small == null){
            small = small_maybe;
        }

        //如果题目诳我，我就返回null
        if(big == null || small == null){
            return null;
        }

        return new int[]{small, big};
    }

    private void inOrder(TreeNode root){
        if(root != null){
            inOrder(root.left);
            list.add(root.val);
            inOrder(root.right);
        }
    }
}
