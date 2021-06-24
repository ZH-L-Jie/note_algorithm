package link;
import java.util.Stack;
/**
 * @Author LHJ_Jack
 * @Description 两个单链表相加生成新的链表
 * @Date 2021/04/17 星期六 20:19
 */
public class TwoLinkAdd {
    static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    /**
     * 双栈实现
     *
     * @param head1
     * @param head2
     * @return
     */
    Node addLinkList1(Node head1, Node head2) {
        Stack<Integer> s1 = new Stack<Integer>();
        Stack<Integer> s2 = new Stack<Integer>();
        //压入栈，栈顶就是个位数
        while (head1 != null) {
            s1.push(head1.val);
            head1 = head1.next;
        }
        while (head2 != null) {
            s2.push(head2.val);
            head2 = head2.next;
        }

        int ca = 0;//进位
        int n1 = 0;//s1出栈的数
        int n2 = 0;//s2出栈的数
        int n = 0;//两数之和
        Node node = null, pre = null;//用于节点拼接
        while (!s1.isEmpty() || !s2.isEmpty()) {
            n1 = s1.isEmpty() ? 0 : s1.pop();
            n2 = s2.isEmpty() ? 0 : s2.pop();
            n = n1 + n2 + ca;
            pre = node;
            node = new Node(n % 10);
            node.next = pre;
            ca = n / 10;
        }
        //处理最后一位的进位
        if (ca == 1) {
            pre = node;
            node = new Node(1);
            node.next = pre;
        }
        return node;

    }

    /**
     * 反转两链表，遍历相加
     *
     * @param head1
     * @param head2
     * @return
     */
    Node addLinkList2(Node head1, Node head2) {
        head1 = reverseLink(head1);
        head2 = reverseLink(head2);
        int ca = 0, n1 = 0, n2 = 0, n = 0;
        Node c1 = head1;
        Node c2 = head2;
        Node node = null;
        Node pre = null;
        while (c1 != null || c2 != null) {
            n1 = c1 != null ? c1.val : 0;
            n2 = c2 != null ? c2.val : 0;
            n = n1 + n2 + ca;
            pre = node;
            node = new Node(n % 10);
            node.next = pre;
            ca = n / 10;
            c1 = c1 != null ? c1.next : null;
            c2 = c2 != null ? c2.next : null;
        }
        if (ca == 1) {
            pre = node;
            node = new Node(1);
            node.next = pre;
        }
        return node;

    }

    /**
     * 反转链表
     * @param head
     * @return
     */
    Node reverseLink(Node head) {
        Node pre = null, next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        Node head1 = new Node(9);
        Node head2 = new Node(1);
        TwoLinkAdd test = new TwoLinkAdd();
        Node node1 = test.addLinkList1(head1, head2);
        Node node2 = test.addLinkList2(head1, head2);
    }

}
