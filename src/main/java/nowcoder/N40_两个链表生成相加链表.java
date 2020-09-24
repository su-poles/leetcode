package nowcoder;

import nowcoder.domain.ListNode;
import nowcoder.util.ListNodeUtil;

import java.util.Stack;

/**
*********************************************************************
* 
* @author poles
* @date 2020/9/22 5:08 下午
*
*********************************************************************
*/
public class N40_两个链表生成相加链表 {
    /**
     *
     * @param head1 ListNode类
     * @param head2 ListNode类
     * @return ListNode类
     */
    public ListNode addInList (ListNode head1, ListNode head2) {
        if(head1 == null) return head2;
        if(head2 == null) return head1;

        Stack<ListNode> s1 = convertStack(head1);
        Stack<ListNode> s2 = convertStack(head2);
        Stack<ListNode> s3 = new Stack<>();

        int overFlow = 0;
        while(!s1.isEmpty() && !s2.isEmpty()){
            //不用重新开辟空间，直接用原有Node对象即可
            ListNode node = s1.pop();
            int val = node.val + s2.pop().val + overFlow;
            overFlow = val > 9 ? 1 : 0;               //计算进位
            node.val = val % 10;
            node.next = null;
            s3.push(node);
        }

        while(!s1.isEmpty()){
            ListNode node = s1.pop();
            int val = node.val + overFlow;
            overFlow = val > 9 ? 1 : 0;               //计算进位
            node.val = val % 10;
            node.next = null;
            s3.push(node);
        }

        while(!s2.isEmpty()){
            //不用重新开辟空间，直接用原有Node对象即可
            ListNode node = s2.pop();
            int val = node.val + overFlow;
            overFlow = val > 9 ? 1 : 0;               //计算进位
            node.val = val % 10;
            node.next = null;
            s3.push(node);
        }

        //如果有进位，则最后还得把进位放进去
        if(overFlow > 0){
            s3.push(new ListNode(overFlow));
        }

        ListNode head = s3.pop();
        ListNode tail = head;
        while(!s3.isEmpty()){
            tail.next = s3.pop();
            tail = tail.next;
        }

        return head;
    }

    private Stack<ListNode> convertStack(ListNode head){
        Stack<ListNode> stack = new Stack<>();

        ListNode current = head;
        while(current != null){
            stack.push(current);
            current = current.next;
        }
        return stack;
    }


    public static void main(String[] args) {
        ListNode t1 = ListNodeUtil.createListNode(new int[]{9,3,7});
        ListNode t2 = ListNodeUtil.createListNode(new int[]{6,3});

        N40_两个链表生成相加链表 n = new N40_两个链表生成相加链表();
        ListNode t3 = n.addInList(t1, t2);
        System.out.println(t3);
    }
}
