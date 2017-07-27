package com.lxl.dataStructures.node;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 复制图---尚有缺陷
 * 待优化
 *
 * @author lxl
 */
public class DeepCopyNodeM {

    private Map<NodeM, Integer> map = new HashMap<NodeM, Integer>();
    private Map<Integer, NodeM> mapcopy = new HashMap<Integer, NodeM>();
    private Map<Integer, Integer[]> mapArr = new HashMap<Integer, Integer[]>();
    private Integer nodeFlag = 0;

    public void copyAllNode(NodeM node) {
        int nodeSize = node.getNodeSize();
        NodeM[] nodeArr = node.getNodeArr();
        if (map.get(node) == null) {
            map.put(node, nodeFlag);
            mapcopy.put(nodeFlag, node);
            nodeFlag++;
        }
        for (NodeM nodeTemp : nodeArr) {
            if (map.get(node) == null) {
                this.copyAllNode(nodeTemp);
            }
        }
    }

    public void deepCopyArr(NodeM node) {
        this.copyAllNode(node);
        Set<NodeM> set = map.keySet();
        for (NodeM nodem : set) {
            Integer[] intArr = null;
            int i = 0;
            for (NodeM nodemF : nodem.getNodeArr()) {
                if (map.get(nodemF) != null) {
                    intArr[i] = map.get(nodemF);
                }
            }
            mapArr.put(map.get(set), intArr);
        }
    }

    public NodeM deepCopy(NodeM node) {
        this.deepCopyArr(node);
        for (Entry<NodeM, Integer> set : map.entrySet()) {
            NodeM nodem = new NodeM();
            int thisNodeFlag = set.getValue();
            NodeM thisNode = set.getKey();
            nodem.setNodeSize(thisNode.getNodeSize());
            nodem.setNodeArr(null);
            map.remove(thisNode, thisNodeFlag);
            map.put(nodem, thisNodeFlag);
            mapcopy.remove(thisNodeFlag, thisNode);
            mapcopy.put(thisNodeFlag, nodem);
        }
        for (Entry<NodeM, Integer> set2 : map.entrySet()) {
            NodeM thisNode = set2.getKey();
            int thisNodeFlag = set2.getValue();
            NodeM[] nodeMarr = null;
            int i = 0;
            Integer[] iarr = mapArr.get(thisNodeFlag);
            if (iarr == null) {
                System.out.println("null");
            } else {
                for (int j : iarr) {
                    nodeMarr[i] = mapcopy.get(iarr[j]);
                    i++;
                }
            }

            thisNode.setNodeArr(nodeMarr);
        }
        Set<NodeM> set = map.keySet();
        NodeM newNodeM = null;
        for (NodeM nodem : set) {
            newNodeM = nodem;
        }
        return newNodeM;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
