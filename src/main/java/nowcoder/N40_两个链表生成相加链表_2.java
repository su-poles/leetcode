package nowcoder;

import nowcoder.domain.ListNode;
import nowcoder.util.ListNodeUtil;

import java.util.Stack;

/**
*********************************************************************
* 
* @author poles
* @date 2020/9/22 5:08 下午
* 效率要稍微高一些的，不然牛客网执行超时过不去
*********************************************************************
*/
public class N40_两个链表生成相加链表_2 {
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
        head2 = reverse(head2);
        ListNode head3 = null;

        int overFlow = 0;
        while(head1 != null && head2 != null){
            int val = head1.val + head2.val + overFlow;
            overFlow = val > 9 ? 1 : 0;
            ListNode node = new ListNode(val % 10);
            if(head3 == null){
                head3 = node;
            }else{
                node.next = head3;
                head3 = node;
            }

            ListNode head1_next = head1.next;
            ListNode head2_next = head2.next;

            head1.next = null;  //help GC
            head2.next = null;  //help GC

            head1 = head1_next;
            head2 = head2_next;
        }

        while(head1 != null){
            int val = head1.val + overFlow;
            overFlow = val > 9 ? 1 : 0;
            ListNode node = new ListNode(val % 10);
            if (head3 != null) {
                node.next = head3;
            }
            head3 = node;

            ListNode head1_next = head1.next;
            head1.next = null;  //help GC
            head1 = head1_next;
        }

        while(head2 != null){
            int val = head2.val + overFlow;
            overFlow = val > 9 ? 1 : 0;
            ListNode node = new ListNode(val % 10);
            if (head3 != null) {
                node.next = head3;
            }
            head3 = node;

            ListNode head2_next = head2.next;
            head2.next = null;  //help GC
            head2 = head2_next;
        }

        if(overFlow > 0){
            ListNode node = new ListNode(overFlow);
            node.next = head3;
            head3 = node;
        }

        return head3;
    }

    private ListNode reverse(ListNode head){
        //断开首节点和第二个节点，从第二个节点开始一次循环遍历添加到第一个节点之前
        ListNode head2 = head.next;
        if(head2 == null){
            return head;
        }

        head.next = null;           //第一个元素和第二个元素断开连接，形成两个链表

        while(head2 != null){
            ListNode next = head2.next;         //获取第二个链表的第二个元素
            head2.next = head;                  //将第二个链表的头结点设置到第一个链表的头结点处
            head = head2;                       //第一个链表的头结点依然使用head指向
            head2 = next;                       //head2指向next，即head2依然作为第二个链表的头结点
        }

        return head;
    }


    public static void main(String[] args) {
        ListNode t1 = ListNodeUtil.createListNode(new int[]{9,3,7});
        ListNode t2 = ListNodeUtil.createListNode(new int[]{6,3});
        N40_两个链表生成相加链表_2 n = new N40_两个链表生成相加链表_2();
        System.out.println(n.addInList(t1, t2));
    }
}
