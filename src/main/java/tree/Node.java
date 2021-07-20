package tree;


import java.util.Stack;

/**
 * @Author LHJ_Jack
 * @Description 二叉树遍历问题
 * @Date 2021/04/30 星期五 14:05
 */
public class Node {

    int val;
    Node left;
    Node right;

    Node(int data) {
        this.val = data;
    }

    /**
     * 递归，先序遍历
     *
     * @param head
     */
    public void preOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        System.out.println(head.val + " ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    /**
     * 递归，中序遍历
     *
     * @param head
     */
    public void inOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        inOrderRecur(head.left);
        System.out.println(head.val + " ");
        inOrderRecur(head.right);
    }

    /**
     * 递归，后序遍历
     *
     * @param head
     */
    public void posOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        posOrderRecur(head.left);
        posOrderRecur(head.right);
        System.out.println(head.val + " ");
    }

    /**
     * 非递归：先序遍历
     *
     * @param head
     */
    public void preOrderUnRecur(Node head) {
        System.out.println("非递归：先序遍历:");
        if (head != null) {
            Stack<Node> stack = new Stack<Node>();
            stack.add(head);
            while (!stack.isEmpty()) {
                head = stack.pop();
                System.out.println(head.val + " ");
                if (head.right != null) {
                    stack.push(head.right);
                }
                if (head.left != null) {
                    stack.push(head.left);
                }
            }
        }
        System.out.println();
    }

    /**
     * 非递归：中序遍历
     *
     * @param head
     */
    public void inOrderUnRecur(Node head) {
        System.out.println("非递归：中序遍历:");
        if (head != null) {
            Stack<Node> stack = new Stack<Node>();
            while (!stack.isEmpty() || head != null) {
                while (!stack.isEmpty() || head != null) {
                    if (head != null) {
                        stack.push(head);
                        head = head.left;
                    } else {
                        head = stack.pop();
                    }
                    System.out.println(head.val + " ");
                    head = head.right;
                }
            }
        }
        System.out.println();
    }

    /**
     * 非递归：后序遍历，前序遍历倒过来，用两个栈
     *
     * @param head
     */
    public void posOrderUnRecur(Node head) {
        System.out.println("非递归：后序遍历:");
        if (head != null) {
            Stack<Node> stack1 = new Stack<Node>();
            Stack<Node> stack2 = new Stack<Node>();
            stack1.push(head);
            while (!stack1.isEmpty()) {
                head = stack1.pop();
                stack2.push(head);
                if (head.left != null) {
                    stack1.push(head.left);
                }
                if (head.right != null) {
                    stack1.push(head.right);
                }
            }
            while (!stack2.isEmpty()) {
                System.out.println(stack2.pop().val + " ");
            }
        }
        System.out.println();
    }

    /**
     * morris遍历模板
     * @param head
     */
    public void morris(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            // 如果当前cur有左子树
            if (mostRight != null) {
                // 找到cur左子树最右的节点
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // 从上面的while里出来后，mostRight就是cur左子树上最右的节点
                if (mostRight.right == null) {   // 如果mostRight.right指向null
                    mostRight.right = cur;    //让其指向cur
                    cur = cur.left;     //cur向左移动
                    continue;       //回到最外层的while，继续判断cur的情况
                } else {     //如果mostRight.right是指向cur的
                    mostRight.right = null;   //让其指向null
                }
            }
            // cur如果没有左子树，cur向右移动
            // 或者cur左子树上最右节点的右指针是指向cur的，cur向右移动
            cur = cur.right;
        }
    }

    /**
     * morris的先序遍历
     *
     * @param head
     */
    public void morrisPre(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            // 如果当前cur有左子树
            if (mostRight != null) {
                // 找到cur左子树最右的节点
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // 从上面的while里出来后，mostRight就是cur左子树上最右的节点
                if (mostRight.right == null) {   // 如果mostRight.right指向null
                    mostRight.right = cur;    //让其指向cur

                    System.out.println(cur.val + ""); //**打印行为**

                    cur = cur.left;     //cur向左移动
                    continue;       //回到最外层的while，继续判断cur的情况
                } else {     //如果mostRight.right是指向cur的
                    mostRight.right = null;   //让其指向null
                }
            } else {
                System.out.println(cur.val + ""); //**打印行为**
            }
            // cur如果没有左子树，cur向右移动
            // 或者cur左子树上最右节点的右指针是指向cur的，cur向右移动
            cur = cur.right;
        }
        System.out.println();
    }

    /**
     * morris的中序遍历
     *
     * @param head
     */
    public void morrisIn(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            // 如果当前cur有左子树
            if (mostRight != null) {
                // 找到cur左子树最右的节点
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // 从上面的while里出来后，mostRight就是cur左子树上最右的节点
                if (mostRight.right == null) {   // 如果mostRight.right指向null
                    mostRight.right = cur;    //让其指向cur
                    cur = cur.left;     //cur向左移动
                    continue;       //回到最外层的while，继续判断cur的情况
                } else {     //如果mostRight.right是指向cur的
                    mostRight.right = null;   //让其指向null
                }
            }
            // cur如果没有左子树，cur向右移动
            // 或者cur左子树上最右节点的右指针是指向cur的，cur向右移动
            System.out.println(cur.val + "");  //打印行为
            cur = cur.right;
        }
        System.out.println();
    }


    /**
     * morris的后序遍历
     *
     * @param head
     */
    public void morrisPos(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            // 如果当前cur有左子树
            if (mostRight != null) {
                // 找到cur左子树最右的节点
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // 从上面的while里出来后，mostRight就是cur左子树上最右的节点
                if (mostRight.right == null) {   // 如果mostRight.right指向null
                    mostRight.right = cur;    //让其指向cur
                    cur = cur.left;     //cur向左移动
                    continue;       //回到最外层的while，继续判断cur的情况
                } else {     //如果mostRight.right是指向cur的
                    mostRight.right = null;   //让其指向null
                    printEdge(cur.left);
                }
            }
            // cur如果没有左子树，cur向右移动
            // 或者cur左子树上最右节点的右指针是指向cur的，cur向右移动
            cur = cur.right;
        }
        printEdge(head);
        System.out.println();
    }

    /**
     * 打印树枝
     *
     * @param head
     */
    public static void printEdge(Node head) {
        Node tail = reverseEdge(head);
        Node cur = tail;
        while (cur != null) {
            System.out.println(cur.val + " ");
            cur = cur.right;
        }
        reverseEdge(tail);
    }

    /**
     * 反转树枝:实际就是反转树的树枝（链表）
     *
     * @param from
     * @return
     */
    public static Node reverseEdge(Node from) {
        Node pre = null;
        Node next = null;
        while (from != null) {
            next = from.right;
            from.right = pre;
            pre = from;
            from = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);

        node1.right = node3;
        node1.left = node2;

        node2.right = node5;
        node2.left = node4;

        node3.right = node7;
        node3.left = node6;

        node1.morrisIn(node1);
    }


}
