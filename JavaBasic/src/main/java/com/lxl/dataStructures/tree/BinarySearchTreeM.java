package com.lxl.dataStructures.tree;

import java.util.Stack;

/**
 * 二叉搜索树 利用基类NodeM
 *
 * @author lxl
 */
public class BinarySearchTreeM {

    private NodeM root;

    public BinarySearchTreeM() {
        root = null;
    }

    public NodeM find(int key) {
        NodeM current = root;
        while (current.iData != key) {
            if (key < current.iData) {
                current = current.leftChild;
            } else {
                current = current.rightChild;
            }
            if (current == null) {
                return null;
            }
        }
        return current;
    }

    public void insert(int id, double dd) {
        NodeM newNode = new NodeM();
        newNode.iData = id;
        newNode.dData = dd;
        if (root == null) {
            root = newNode;
        } else {
            NodeM current = root;
            NodeM parent;
            while (true) {
                parent = current;
                if (id < current.iData) {
                    current = current.leftChild;
                    if (current == null) {
                        parent.leftChild = newNode;
                        return;
                    }
                } else {
                    current = current.rightChild;
                    if (current == null) {
                        parent.rightChild = newNode;
                        return;
                    }
                }
            }
        }
    }

    public boolean delete(int key) {
        NodeM current = root;
        NodeM parent = root;
        boolean isleftChild = true;

        while (current.iData != key) {
            parent = current;
            if (key < current.iData) {
                isleftChild = true;
                current = current.leftChild;
            } else {
                isleftChild = false;
                current = current.rightChild;
            }
            if (current == null) {
                return false;
            }
        }

        if (current.leftChild == null && current.rightChild == null) {
            if (current == root) {
                root = null;
            } else if (isleftChild) {
                parent.leftChild = null;
            } else {
                parent.rightChild = null;
            }
        } else if (current.leftChild == null) {
            if (current == root) {
                root = current.rightChild;
            } else if (isleftChild) {
                parent.leftChild = current.rightChild;
            } else {
                parent.rightChild = current.rightChild;
            }
        } else {
            NodeM successor = getSuccessor(current);
            if (current == root) {
                root = successor;
            } else if (isleftChild) {
                parent.leftChild = successor;
            } else {
                parent.rightChild = successor;
            }
        }
        return true;
    }

    private NodeM getSuccessor(NodeM delNode) {
        NodeM successorParent = delNode;
        NodeM successor = delNode;
        NodeM current = delNode.rightChild;
        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.leftChild;
        }
        if (successor != delNode.rightChild) {
            successorParent.leftChild = successor.rightChild;
            successor.rightChild = delNode.rightChild;
        }
        return successor;
    }

    public void traverse(int traverseType) {
        switch (traverseType) {
            case 1:
                System.out.print("\nPreorder traversal: ");
                perOrder(root);
                break;
            case 2:
                System.out.print("\nPreorder traversal: ");
                inOrder(root);
                break;
            case 3:
                System.out.print("\nPreorder traversal: ");
                postOrder(root);
                break;
        }
        System.out.println("");
    }

    private void postOrder(NodeM localRoot) {
        if (localRoot != null) {
            postOrder(localRoot.leftChild);
            postOrder(localRoot.rightChild);
            System.out.print(localRoot.iData + " ");
        }
    }

    private void inOrder(NodeM localRoot) {
        if (localRoot != null) {
            inOrder(localRoot.leftChild);
            System.out.print(localRoot.iData + " ");
            inOrder(localRoot.rightChild);
        }
    }

    private void perOrder(NodeM localRoot) {
        if (localRoot != null) {
            System.out.print(localRoot.iData + " ");
            perOrder(localRoot.leftChild);
            perOrder(localRoot.rightChild);
        }
    }

    public void displayTree() {
        Stack globalStack = new Stack();
        globalStack.push(root);
        int nBlanks = 32;
        boolean isRowEmpty = false;
        System.out.println("--------------------------");
        while (isRowEmpty == false) {
            Stack localStack = new Stack();
            isRowEmpty = true;
            for (int j = 0; j < nBlanks; j++) {
                System.out.print(" ");
            }

            while (globalStack.isEmpty() == false) {
                NodeM temp = (NodeM) globalStack.pop();
                if (temp != null) {
                    System.out.print(temp.iData);
                    localStack.push(temp.leftChild);
                    localStack.push(temp.rightChild);

                    if (temp.leftChild != null || temp.rightChild != null) {
                        isRowEmpty = false;
                    }
                } else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < nBlanks * 2 - 2; j++) {
                    System.out.print(" ");
                }
            }
            System.out.println("");
            nBlanks /= 2;
            while (localStack.isEmpty() == false) {
                globalStack.push(localStack.pop());
            }
            System.out.println("-----------------------------");
        }

    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
