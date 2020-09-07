package nowcoder.domain;

import java.util.ArrayList;

/**
*********************************************************************
* 
* @author poles
* @date 2020/8/29 10:36 上午
*
*********************************************************************
*/
public class TreeNode {
    public int val = 0;
    public TreeNode left = null;
    public TreeNode right = null;


    @Override
    public String toString() {
        return toString(preOrder(this));
    }

    public String preOrderToString(){
        return toString(preOrder(this));
    }

    public String inOrderToString(){
        return toString(inOrder(this));
    }

    public String postOrderToString(){
        return toString(postOrder(this));
    }

    private String toString(ArrayList<Integer> list){
        StringBuilder sb = new StringBuilder("{");
        for(Integer val : list){
            sb.append(val).append(",");
        }
        sb.delete(sb.length() - 1, sb.length());
        sb.append("}");
        return sb.toString();
    }

    private ArrayList<Integer> preOrder(TreeNode root){
        ArrayList<Integer> list = new ArrayList<>();

        list.add(root.val);

        if(root.left != null){
            list.addAll(preOrder(root.left));
        }

        if(root.right != null){
            list.addAll(preOrder(root.right));
        }
        return list;
    }

    private ArrayList<Integer> inOrder(TreeNode root){
        ArrayList<Integer> list = new ArrayList<>();

        if(root.left != null){
            list.addAll(inOrder(root.left));
        }

        list.add(root.val);

        if(root.right != null){
            list.addAll(inOrder(root.right));
        }
        return list;
    }

    private ArrayList<Integer> postOrder(TreeNode root){
        ArrayList<Integer> list = new ArrayList<>();

        if(root.left != null){
            list.addAll(postOrder(root.left));
        }

        if(root.right != null){
            list.addAll(postOrder(root.right));
        }

        list.add(root.val);
        return list;
    }
}
