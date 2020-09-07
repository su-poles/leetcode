package nowcoder;

import nowcoder.domain.ListNode;

/**
*********************************************************************
* 
* @author poles
* @date 2020/8/27 2:00 下午
*
*********************************************************************
*/
public class 链表是否有环 {

    public boolean hasCycle(ListNode head) {
        if(head == null){
            return false;
        }

        //使用快慢指针来解题
        ListNode fast = head;
        ListNode slow = head;
        for(;;){
            if(fast.next == null || fast.next.next == null){
                return false;
            }

            fast = fast.next.next;
            slow = slow.next;

            if(slow == fast){
                return true;
            }
        }
    }

    public static void main(String[] args) {

    }
}
