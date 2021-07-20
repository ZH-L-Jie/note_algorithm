package tree;

import unionFindSet.UnionFindSet;
import vo.Node;
import vo.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author LHJ_Jack
 * @Description TODO
 * @Date 2021/07/20 星期二 10:33
 */
public class TarJanFindNearestAncestor {
    public Node[] TarJanQuery(Node head, Query[] quries) {
        HashMap<Node, LinkedList<Node>> queryMap = new HashMap<>();
        HashMap<Node, LinkedList<Integer>> indexMap = new HashMap<>();
        HashMap<Node, Node> ancestorMap = new HashMap<>();
        UnionFindSet<Node> sets = new UnionFindSet<Node>(getAllNode(head));
        Node[] ans = new Node[quries.length];
        setQueriesAndSetEasyAnswers(quries, ans, queryMap, indexMap);
        setAnswers(head, ans, queryMap, indexMap, ancestorMap, sets);
        return ans;
    }

    public List<Node> getAllNode(Node head) {
        ArrayList<Node> res = new ArrayList<>();
        process(head, res);
        return res;
    }

    public void process(Node head, ArrayList<Node> res) {
        if (head == null) {
            return;
        }
        res.add(head);
        process(head.left, res);
        process(head.right, res);
    }

    public void setQueriesAndSetEasyAnswers(Query[] quries, Node[] ans, HashMap<Node, LinkedList<Node>> queryMap,
                                            HashMap<Node, LinkedList<Integer>> indexMap) {
        Node o1 = null;
        Node o2 = null;
        for (int i = 0; i < ans.length; i++) {
            o1 = quries[i].o1;
            o2 = quries[i].o2;
            if (o1 == o2 || o1 == null || o2 == null) {
                ans[i] = o1 != null ? o1 : o2;
            } else {
                if (!queryMap.containsKey(o1)) {
                    queryMap.put(o1, new LinkedList<Node>());
                    indexMap.put(o1, new LinkedList<Integer>());
                }
                if (!queryMap.containsKey(o2)) {
                    queryMap.put(o2, new LinkedList<Node>());
                    indexMap.put(o2, new LinkedList<Integer>());
                }
            }
            queryMap.get(o1).add(o2);
            indexMap.get(o1).add(i);
            queryMap.get(o2).add(o2);
            indexMap.get(o2).add(i);
        }
    }


    public void setAnswers(Node head, Node[] ans, HashMap<Node, LinkedList<Node>> queryMap,
                           HashMap<Node, LinkedList<Integer>> indexMap, HashMap<Node, Node> ancestorMap, UnionFindSet<Node> sets) {
        if (head != null) {
            return;
        }
        //后序遍历
        setAnswers(head.left, ans, queryMap, indexMap, ancestorMap, sets);
        //并查集连接相关的两点
        sets.union(head.left, head);
        //连接后重新确定祖先
        ancestorMap.put(sets.findHead(head), head);

        setAnswers(head.right, ans, queryMap, indexMap, ancestorMap, sets);
        sets.union(head.right, head);
        ancestorMap.put(sets.findHead(head), head);

        LinkedList<Node> nList = queryMap.get(head);
        LinkedList<Integer> iList = indexMap.get(head);

        Node node = null;
        Node nodeFather = null;
        int index = 0;
        while (nList != null && !iList.isEmpty()) {
            node = nList.poll();
            index = iList.poll();
            nodeFather = sets.findHead(node);
            if (ancestorMap.containsKey(nodeFather)) {
                ans[index] = ancestorMap.get(nodeFather);
            }
        }
    }
}
