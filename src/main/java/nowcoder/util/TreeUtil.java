package nowcoder.util;

import nowcoder.domain.TreeNode;

import java.util.HashMap;

/**
*********************************************************************
* 
* @author poles
* @date 2020/8/29 10:34 上午
*  根据输入的字符串，生成一棵原始的树，或者构建一棵二叉查询数
*********************************************************************
*/
public class TreeUtil {
    public static void main(String[] args) {
        String str = "{34,20,38,3,29,35,44,2,4,24,30,#,37,42,45,1,#,#,12,23,26,#,32,36,#,40,21,#,#,#,#,5,15,43,#,25,28,31,33,#,#,39,41,#,#,#,8,14,18,#,22,#,#,27,#,#,#,#,#,#,#,#,#,6,10,13,#,16,19,#,#,#,#,#,7,9,11,#,#,#,17}";
        TreeNode root = buildOriginal(str);
        System.out.println(root.inOrderToString());
    }

    /**
     * 根据给定的元素构建一颗普通树二叉树，注意，不是搜索树，是按照给定的元素顺序直接拼接而成的， #号纯属为占位符，按照每层来构建
     * 输入内容： {34,20,38,3,29,35,44,2,4,24,30,#,37,42,45,1,#,#,12,23,26,#,32,36,#,40,21,#,#,#,#,5,15,43,#,25,28,31,33,#,#,39,41,#,#,#,8,14,18,#,22,#,#,27,#,#,#,#,#,#,#,#,#,6,10,13,#,16,19,#,#,#,#,#,7,9,11,#,#,#,17}
     * 输出内容：TreeNode root
     * 以上用例中，有两个元素是一个二叉查找树调换了位置的，试试找出来是哪两个元素
     *
     * 以上输入内容，实际上是这棵树的按层遍历的结果，按层遍历二叉树原理
     *  1. 把根节点塞进队列， 好，塞入完成，开始从队列取结果；
     *  2. 每取一个元素，将其左节点和右节点塞入队列，取出来的节点放在一个有序集合中
     *  3. 继续从队列里取，取出的元素的左右节点继续往队列塞
     *  4. 如此往复循环，直到队列空了为止。
     *
     *
     * 怎么根据按层遍历的结果，恢复原来的树呢？我想了半天，打算使用一个招：
     * root节点的索引值为1，左子节点就是2，右子节点就是3， 对于节点2来说，左子节点就是4，右子节点就是5，自上而下，从root开始，直接根据每个节点的索引值从输入的数组里找对应的值即可，试一下：
     *
     * 做的过程中发现一个问题：如果中间某个节点是#，比如索引11对应就是#，那么其是没有子节点的，但问题是对应的左子节点索引位2 * 11 + 1和右子节点索引位2*11+2都对应了值，即36和#
     * 所以，从第一个#号开始，每次出现一个#号，之后的索引为都要相应减去2，比如当前节点的索引值为n, 则其左子节点索引值为：2 * n + 1 - 2*#的个数
     */
    public static TreeNode buildOriginal(String str){
        str = str.substring(1);
        str = str.substring(0, str.length() - 1);
        String[] vals = str.split(",");

        HashMap<Integer, TreeNode> map = new HashMap<>();
        //先初始化root节点
        TreeNode root = new TreeNode();
        root.val = Integer.parseInt(vals[0]);
        map.put(0, root);

        //#节点的个数
        int num = 0;

        //从第一个元素开始，到最后一个元素，只需要把每个元素的left和right赋上值就可以了， hashMap方便根据索引，找到对应的TreeNode节点
        for(int i = 0; i < vals.length; i++){
            //对于那些#号的节点，直接跳过，它没有子节点，不用处理
            if("#".equals(vals[i])){
                num++;
                continue;
            }

            //直接从map中取当前节点
            TreeNode node = map.get(i);

            //给当前节点赋值left
            int leftIndex = i * 2 + 1 - 2 * num;     //左子节点的索引：i * 2 + 1 减去 前面#号几点占用的位置
            if(leftIndex > vals.length - 1){
                //后面没有了，整棵树就构建完了， 当前节点的左节点已经不在vals数组中了，都超过vals范围了，说白了就是后面连续的一堆###都省略了
                break;
            }
            String leftVal = vals[leftIndex];
            if(!"#".equals(leftVal)){
                TreeNode left = new TreeNode();
                left.val = Integer.parseInt(leftVal);
                //新创建的节点要加入到map中，下次直接取
                map.put(leftIndex, left);

                //给node设置左子节点
                node.left = left;
            }

            //给当前节点赋值right
            int rightIndex = leftIndex + 1;
            if(rightIndex > vals.length - 1){
                //后面没有了，整棵树就构建完了， 当前节点正在找其右子节点，发现已经没有元素，所以后面的元素就都没有左右子节点了。
                break;
            }
            String rightVal = vals[rightIndex];
            if(!"#".equals(rightVal)){
                TreeNode right = new TreeNode();
                right.val = Integer.parseInt(rightVal);

                //新创建的节点要加入到map中，下次直接取
                map.put(rightIndex, right);

                //给node设置右子节点
                node.right = right;
            }
        }

        return root;
    }

    /**
     * 构建一棵普通查询二叉搜索树
     * 输入内容： {34,20,38,3,29,35,44,2,4,24,30,#,37,42,45,1,#,#,12,23,26,#,32,36,#,40,21,#,#,#,#,5,15,43,#,25,28,31,33,#,#,39,41,#,#,#,8,14,18,#,22,#,#,27,#,#,#,#,#,#,#,#,#,6,10,13,#,16,19,#,#,#,#,#,7,9,11,#,#,#,17}
     * 返回：TreeNode root
     */
    public static TreeNode build(String str){
        str = str.substring(1).substring(0, str.length() - 2);
        String[] vals = str.split(",");

        TreeNode root = null;
        for(String val : vals){
            if(val.equals("#")){
                continue;
            }
            if(root == null){
                TreeNode node = new TreeNode();
                node.val = Integer.parseInt(val);
                root = node;
            }else{
                add(root, Integer.parseInt(val));
            }
        }

        return root;
    }

    private static void add(TreeNode root, int val){
        if(val < root.val){
            if(root.left == null){
                TreeNode node = new TreeNode();
                node.val = val;
                root.left = node;
            }else{
                add(root.left, val);
            }
        }else if(val > root.val){
            if(root.right == null){
                TreeNode node = new TreeNode();
                node.val = val;
                root.right = node;
            }else{
                add(root.right, val);
            }
        }else{
            //覆盖当前节点
        }
    }

}
