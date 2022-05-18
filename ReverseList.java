package nowcoder;

// 反转链表
public class ReverseList {
    public ListNode ReverseListTest(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode end = null;    // 看作原链表的最后一个结点
        while (head != null) {
            ListNode mid = head.next;
            head.next = end;
            end = head;    // 赋值顺序不能变
            head = mid;
        }
        return end;
    }

    public static void main(String[] args){
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;
        ReverseList r = new ReverseList();
        ListNode res = r.ReverseListTest(node1);
    }
}
