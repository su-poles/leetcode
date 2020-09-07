package nowcoder;

import nowcoder.domain.ListNode;

import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicReference;

/**
*********************************************************************
* 
* @author poles
* @date 2020/8/27 10:08 上午
*
*********************************************************************
*/
public class 链表反转 {

    public static ListNode ReverseList(ListNode head) {

        //方法一： 使用栈或者队列
        Stack<ListNode> stack = new Stack<>();
        while(head != null){
            ListNode element = new ListNode(head.val);  //这一句非常非常重要，一定要先清空原有的next连接才可以
            stack.push(element);
            head = head.next;
        }

        ListNode newListNode = null;
        while(!stack.isEmpty()){
            if(newListNode == null){
                newListNode = stack.pop();
            }else{
                ListNode current = newListNode;
                while(current.next != null){
                    current = current.next;
                }

                current.next = stack.pop();
            }
        }

        return newListNode;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        for(int i = 2; i <= 10; i++){
            ListNode current = head;
            while(current.next != null){
                current = current.next;
            }
            current.next = new ListNode(i);
        }

        //打印head
//        while(head != null){
//            System.out.println(head);
//            head = head.next;
//        }

        ListNode newHead = ReverseList(head);

        //打印newHead
        while(newHead != null){
            System.out.println(newHead);
            newHead = newHead.next;
        }
    }
}

