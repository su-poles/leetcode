package nowcoder.util;

import nowcoder.domain.ListNode;

/**
*********************************************************************
* 
* @author poles
* @date 2020/9/22 5:16 下午
*  牛客网链表相关的操作
*********************************************************************
*/
public class ListNodeUtil {

    /**
     * 生成链表并返回
     * 传入一个数组，如{7,3,9}
     * 返回一个链表：7->3->9
     * @return nowcoder.domain.ListNode
     */
    public static ListNode createListNode(int[] arr){
        if(arr.length == 0){
            return null;
        }

        ListNode head = new ListNode(arr[arr.length - 1]);
        if(arr.length > 1){
            for(int i = arr.length - 2; i >= 0; i--){
                ListNode node = new ListNode(arr[i]);
                node.next = head;
                head = node;
            }
        }

        return head;
    }

    public static void main(String[] args) {
        int[] a = {3,7,9};
        ListNode t = createListNode(a);
        System.out.println(t);
    }
}
