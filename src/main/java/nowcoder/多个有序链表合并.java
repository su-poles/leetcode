package nowcoder;

import nowcoder.domain.ListNode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

/**
*********************************************************************
* 
* @author poles
* @date 2020/8/28 8:35 下午
*
*********************************************************************
*/
public class 多个有序链表合并 {
    public static void main(String[] args) {
        ArrayList<ListNode> list = new ArrayList<>();
        ListNode node = new ListNode(1); node.next = new ListNode(2); node.next.next = new ListNode(2);
        ListNode node2 = new ListNode(1); node2.next = new ListNode(1); node2.next.next = new ListNode(2);
        list.add(node);
        list.add(node2);

        System.out.println(mergeKLists(list));
    }
    public static ListNode mergeKLists(ArrayList<ListNode> lists) {
        //思路一，直接使用树形结构，然后再使用前序遍历，试一下
        TreeSet<ListNode> tree = new TreeSet<>((o1, o2) -> o1.val > o2.val ? 1 : -1);
        for(ListNode head : lists){
            while(head != null){
                ListNode node = head;
                head = head.next;       //走下一个
                node.next = null;
                tree.add(node);         //节点加入tree中
            }
        }

        //前序遍历即可
        ListNode head = null;
        ListNode current = null;
        for(ListNode node : tree){
            if(head == null){
                head = node;
                current = head;
            }else{
                current.next = node;
                current = current.next;
            }
        }

        return head;
    }
}
