package unionFindSet;

import vo.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @Author LHJ_Jack
 * @Description TODO
 * @Date 2021/07/20 星期二 9:10
 */
public class UnionFindSet<V> {
    public HashMap<V, Element<V>> elementMap;
    public HashMap<Element<V>, Element<V>> fatherMap;
    public HashMap<Element<V>, Integer> rankMap;

    public UnionFindSet(List<V> list) {
        elementMap = new HashMap<V, Element<V>>();
        fatherMap = new HashMap<Element<V>, Element<V>>();
        rankMap = new HashMap<Element<V>, Integer>();
        for (V value : list) {
            Element<V> element = new Element<V>(value);
            elementMap.put(value, element);
            fatherMap.put(element, element);
            rankMap.put(element, 1);
        }
    }

    private Element<V> findHead(Element<V> element) {
        Stack<Element<V>> path = new Stack<>();
        while (element != fatherMap.get(element)) {
            path.push(element);
            element = fatherMap.get(element);
        }
        while (!path.isEmpty()) {
            fatherMap.put(path.pop(), element);
        }
        return element;
    }

    public V findHead(V value) {
        return elementMap.containsKey(value) ? findHead(elementMap.get(value)).value : null;
    }

    public boolean isSameSet(V a, V b) {
        if (elementMap.containsKey(a) && elementMap.containsKey(b)) {
            return findHead(elementMap.get(a)) == findHead(elementMap.get(b));
        }
        return false;
    }

    public void union(V a, V b) {
        if (elementMap.containsKey(a) && elementMap.containsKey(b)) {
            Element<V> aF = findHead(elementMap.get(a));
            Element<V> bF = findHead(elementMap.get(b));
            if (aF != bF) {
                Element<V> big = rankMap.get(aF) >= rankMap.get(bF) ? aF : bF;
                Element<V> small = big == aF ? bF : aF;
                fatherMap.put(small, big);
                rankMap.put(big, rankMap.get(aF) + rankMap.get(bF));
                rankMap.remove(small);
            }
        }
    }
}
