package nowcoder;

import nowcoder.domain.ListNode;

/**
*********************************************************************
* 
* @author poles
* @date 2020/8/28 8:40 上午
*
 * 假设链表中每一个节点的值都在 0 - 9 之间，那么链表整体就可以代表一个整数。
 * 给定两个这种链表，请生成代表两个整数相加值的结果链表。
 * 例如：链表 1 为 9->3->7，链表 2 为 6->3，最后生成新的结果链表为 1->0->0->0。
 *
 * 输入用例：[5,9,7,5,7,1,2,6,4,2,7,8,9,6,1,6,6,1,1,4,2,9,5,5,0,4,6,3,0,4,3,5,6,7,0,5,5,4,4,0],[1,3,2,5,0,6,0,2,1,4,3,9,3,0,9,9,0,3,1,6,5,7,8,6,2,3,8,5,0,9,7,9,4,5,9,9,4,9,3,6]
 * 输出用例：{7,3,0,0,7,7,2,8,5,7,1,8,2,7,1,5,6,4,3,0,8,7,4,1,2,8,4,8,1,4,1,5,1,3,0,5,0,3,7,6}
*********************************************************************
*/
public class 链表相加 {
    public static void main(String[] args) {
        int[] s1 = {4,6,0,2,6,6,3,6,3,0,7,8,0,4,1,7,0,5,6,5,2,4,9,9,1,5,1,5};
        int[] s2 = {6,2,7,8,6,4,7,0,9,3,0,3,6,2,5,6,0,9,6,2,7,4,2,7,1,0,9,0,5,6,5,4,9,1,8,9,3,4,0,2,1,8,8,2,2,0};
        ListNode head1 = new ListNode(s1[0]);
        ListNode loopFactor = head1;
        for(int i = 1; i < s1.length; i++){
            loopFactor.next = new ListNode(s1[i]);
            loopFactor = loopFactor.next;
        }

        ListNode head2 = new ListNode(s2[0]);
        loopFactor = head2;
        for(int i = 1; i < s2.length; i++){
            loopFactor.next = new ListNode(s2[i]);
            loopFactor = loopFactor.next;
        }

        链表相加 a = new 链表相加();
        ListNode result = a.addInList(head1, head2);
        System.out.println(result);

    }

    /**
     *
     * @param head1 ListNode类
     * @param head2 ListNode类
     * @return ListNode类
     */
    public ListNode addInList (ListNode head1, ListNode head2) {
        if(head1 == null) return head2;
        if(head2 == null) return head1;

        head1 = reverse(head1);
        System.out.println(head1);
        head2 = reverse(head2);
        System.out.println(head2);
        ListNode resultNode = null;
        ListNode current = null;
        int overflow = 0;
        while(head1 != null && head2 != null){
            int val = head1.val + head2.val + overflow;
            overflow = val / 10;
            if(resultNode == null){
                resultNode = new ListNode(val % 10);
                current = resultNode;
            }else{
                current.next = new ListNode(val % 10);
                current = current.next;
            }

            head1 = head1.next;
            head2 = head2.next;
        }

        while(head1 != null){
            int val = head1.val + overflow;
            overflow = val / 10;
            if(resultNode == null){
                resultNode = new ListNode(val % 10);
                current = resultNode;
            }else{
                current.next = new ListNode(val % 10);
                current = current.next;
            }
            head1 = head1.next;
        }

        while(head2 != null){
            int val = head2.val + overflow;
            overflow = val / 10;
            if(resultNode == null){
                resultNode = new ListNode(val % 10);
                current = resultNode;
            }else{
                current.next = new ListNode(val % 10);
                current = current.next;
            }
            head2 = head2.next;
        }

        return reverse(resultNode);
    }

    private ListNode reverse(ListNode head){
        ListNode newHead = head;
        ListNode current = head.next;
        newHead.next = null;
        while(current != null){
            ListNode next = current.next;
            current.next = newHead;
            newHead = current;
            current = next;
        }

        return newHead;
    }
}
