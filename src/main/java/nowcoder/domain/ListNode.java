package nowcoder.domain;

import java.util.ArrayList;

/**
*********************************************************************
* 
* @author poles
* @date 2020/8/29 10:37 上午
*
*********************************************************************
*/
public class ListNode {
    public int val;
    public ListNode next = null;

    public ListNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        ArrayList<Integer> list = new ArrayList<>();
        ListNode current = this;
        while(current != null){
            list.add(current.val);
            current = current.next;
        }

        StringBuilder result = new StringBuilder("{");
        for(Integer i : list){
            result.append(i).append(",");
        }
        result.delete(result.length() - 1, result.length());
        result.append("}");
        return result.toString();
    }
}
