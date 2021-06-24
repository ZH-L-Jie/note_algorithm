package link;


/**
 * @Author LHJ_Jack
 * @Description 两个单链表的相交问题
 * @Date 2021/04/19 星期一 10:37
 */
public class LinkIntersect {
    static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }


    /**
     * 直链表，相交返回相交节点   单词loop：环
     *
     * @param head1 链表1
     * @param head2 链表2
     * @return
     */
    public Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) return null;
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;//记录链表的长度
        while (cur1.next != null) {
            n++;
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            n--;  // n最后为两链表得长度差
            cur2 = cur2.next;
        }
        if (cur1 != cur2) {  //两链表终点相同则表明有焦点
            return null;
        }

        //相交
        cur1 = n > 0 ? head1 : head2;//cur1记录长的链表   //合理利用三目元算符更可口哦！！！
        cur2 = cur1 == head1 ? head2 : head1;//cur2记录短链表
        // n = Math.abs(n);
        n = n > 0 ? n : -(n);
        //长的链表走到和短链表一样的长度
        while (n != 0) {
            n--;
            cur1 = cur1.next;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    /**
     * 单链表有环则返回入环节点,快慢指针
     * @param head
     * @return
     */
    public Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        //如果有环则最终会相等；如果节点为null则表明不成环
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
             fast = fast.next.next;
            slow= slow.next;
        }
        fast = head;
        //这里快慢指针求换的入环点，原理在网上一大堆，不再累述
        while (fast != slow) {
            fast=fast.next;
            slow=slow.next;
        }
        return slow;
    }
    /**
     * 两个有环的链表是否相交（详解），有则返回相交点
     * 有环有3种情况
     */
    /**
     * @param head1 链表1
     * @param loop1 链表1的入环节点
     * @param head2 链表2
     * @param loop2 链表2的入环节点
     * @return
     */
    public Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node cur1 = null;
        Node cur2 = null;
        //情况1
        if (loop1 == loop2) {
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1 != loop1) {
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2) {
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0) {
                n--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            //情况2、3
            cur1 = loop1.next;
            while (cur1 != loop1) {  //情况2的cur1会等于loop1；情况3则是在cur1等于loop1前会和loop2相等
                if (cur1 == loop2) {
                    return loop1;
                }
                cur1 = cur1.next;
            }
            return null;
        }

    }

    /**
     * 主方法
     *
     * @return
     */
    public Node getIntersectNode(Node head1, Node head2) {
        if (head1==null||head2==null) return null;
        Node loop1 = getLoopNode(head1);//获取链表的入环节点
        Node loop2 = getLoopNode(head2);
        if (loop1 == null && loop2 == null) { //两链表为直链表
            return noLoop(head1, head2);
        }
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);  //两链表为环链表
        }
        return null;
    }

    public static void main(String[] args) {
        //1->2->3->4->5->7  6->7->4
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        node4.next=node5;
        node5.next=node7;
        node6.next=node7;
        node7.next=node4;
        Node head1 = node1;
        Node head2 = node6;
        LinkIntersect linkIntersect = new LinkIntersect();
        Node node = linkIntersect.getIntersectNode(head1, head2);
        System.out.println(node.val);
    }
}
