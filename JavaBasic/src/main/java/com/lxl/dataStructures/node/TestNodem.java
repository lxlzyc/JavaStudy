package com.lxl.dataStructures.node;

import java.util.HashSet;
import java.util.Set;

public class TestNodem {


    private static Set<NodeM> nodeMset = new HashSet<NodeM>();

    //	private boolean nodeflag = false;
    public NodeM getNode() {
        NodeM node1 = new NodeM();
        NodeM node2 = new NodeM();
        NodeM node3 = new NodeM();
        NodeM node4 = new NodeM();
        NodeM[] nodearr1 = {node2, node3};
        NodeM[] nodearr2 = {node1, node4};
        NodeM[] nodearr3 = {node1, node4};
        NodeM[] nodearr4 = {node3, node2};
        node1.setNodeSize(1);
        node2.setNodeSize(2);
        node3.setNodeSize(3);
        node4.setNodeSize(4);
        node1.setNodeArr(nodearr1);
        node2.setNodeArr(nodearr2);
        node3.setNodeArr(nodearr3);
        node4.setNodeArr(nodearr4);
        return node1;
    }

    public void displayAll(NodeM node) {
        nodeMset.clear();
        this.thisaddNodeM(node);
        if (nodeMset == null) {

        } else {
            for (NodeM nodeTemp : nodeMset) {
                System.out.print("节点值：" + nodeTemp.getNodeSize());
                for (NodeM nodeTemp2 : nodeTemp.getNodeArr()) {
                    System.out.print("---所连接节点值：" + nodeTemp2.getNodeSize());
                }
                System.out.println();
            }
        }
    }


    public void thisaddNodeM(NodeM node) {
        if (!nodeMset.contains(node)) {
            nodeMset.add(node);
            for (NodeM nodetemp : node.getNodeArr()) {
                if (!nodeMset.contains(nodetemp)) {
                    thisaddNodeM(nodetemp);
                }
            }
        }

    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        TestNodem test = new TestNodem();
        NodeM node = null;
        node = test.getNode();
        test.displayAll(node);
        System.out.println("=====================");
//		DeepCopyNodeM deppcopy = new DeepCopyNodeM();
//		NodeM newnode = deppcopy.deepCopy(node);
//		test.displayAll(newnode);
//		test.displayAll(node);

        DeepCopyNodeMByCut deepcut = new DeepCopyNodeMByCut();
        NodeM newnode = deepcut.deepCopy(node);
//		 deepcut.get
        test.displayAll(newnode);
        System.out.println("=====================");
        test.displayAll(node);
        System.out.println("=====================");
        NodeM newnode2 = deepcut.deepCopy(newnode);
//		 deepcut.get
        test.displayAll(newnode2);
        System.out.println("=====================");
        test.displayAll(node);


        System.out.println(newnode == node);
        //此复制方法成功
    }

}
