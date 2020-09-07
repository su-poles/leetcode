package nowcoder;

import nowcoder.domain.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
*********************************************************************
* 
* @author poles
* @date 2020/8/28 10:23 上午
*
*********************************************************************
*/
public class 二叉树遍历 {
    private ArrayList<Integer> pre = new ArrayList<>();
    private ArrayList<Integer> in = new ArrayList<>();
    private ArrayList<Integer> post = new ArrayList<>();

    /**
     *
     * @param root TreeNode类 the root of binary tree
     * @return int整型二维数组
     */
    public int[][] threeOrders (TreeNode root) {
        int[][] result = new int[3][];

        //前序遍历
        preOrder(root);
        result[0] = pre.stream().mapToInt(Integer::intValue).toArray();

        //中序遍历
        inOrder(root);
        result[1] = in.stream().mapToInt(Integer::intValue).toArray();

        //后序遍历
        postOrder(root);
        result[2] = post.stream().mapToInt(Integer::intValue).toArray();

        return result;
    }

    private void preOrder(TreeNode root){
        if(root == null){
            return;
        }

        pre.add(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }

    private void inOrder(TreeNode root){
        if(root == null){
            return;
        }

        inOrder(root.left);
        in.add(root.val);
        inOrder(root.right);
    }

    private void postOrder(TreeNode root){
        if(root == null){
            return;
        }

        postOrder(root.left);
        postOrder(root.right);
        post.add(root.val);
    }
}
