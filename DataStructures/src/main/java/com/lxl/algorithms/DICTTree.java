package com.lxl.algorithms;

public class DICTTree {
    private int SIZE = 26;
    private DICTTreeNode root;// �ֵ����ĸ�

    DICTTree() {// ��ʼ���ֵ���
        root = new DICTTreeNode();
    }

    private class DICTTreeNode {// �ֵ����ڵ�
        private int num;// �ж��ٵ���ͨ������ڵ�,���ڵ��ַ����ֵĴ���
        private DICTTreeNode[] son;// ���еĶ��ӽڵ�
        private boolean isEnd;// �ǲ������һ���ڵ�
        private char val;// �ڵ��ֵ

        DICTTreeNode() {
            num = 1;
            son = new DICTTreeNode[SIZE];
            isEnd = false;
        }
    }

    // �����ֵ���
    public void insert(String str) {// ���ֵ����в���һ������
        if (str == null || str.length() == 0) {
            return;
        }
        DICTTreeNode node = root;
        char[] letters = str.toCharArray();
        for (int i = 0, len = str.length(); i < len; i++) {
            int pos = letters[i] - 'a';
            if (node.son[pos] == null) {
                node.son[pos] = new DICTTreeNode();
                node.son[pos].val = letters[i];
            } else {
                node.son[pos].num++;
            }
            node = node.son[pos];
        }
        node.isEnd = true;
    }

    // ���㵥��ǰ׺������
    public int countPrefix(String prefix) {
        if (prefix == null || prefix.length() == 0) {
            return -1;
        }
        DICTTreeNode node = root;
        char[] letters = prefix.toCharArray();
        for (int i = 0, len = prefix.length(); i < len; i++) {
            int pos = letters[i] - 'a';
            if (node.son[pos] == null) {
                return 0;
            } else {
                node = node.son[pos];
            }
        }
        return node.num;
    }

    // ��ӡָ��ǰ׺�ĵ���
    public String hasPrefix(String prefix) {
        if (prefix == null || prefix.length() == 0) {
            return null;
        }
        DICTTreeNode node = root;
        char[] letters = prefix.toCharArray();
        for (int i = 0, len = prefix.length(); i < len; i++) {
            int pos = letters[i] - 'a';
            if (node.son[pos] == null) {
                return null;
            } else {
                node = node.son[pos];
            }
        }
        preTraverse(node, prefix);
        return null;
    }

    // ���������˽ڵ�ĵ���.
    public void preTraverse(DICTTreeNode node, String prefix) {
        if (!node.isEnd) {
            for (DICTTreeNode child : node.son) {
                if (child != null) {
                    preTraverse(child, prefix + child.val);
                }
            }
            return;
        }
        System.out.println(prefix);
    }

    // ���ֵ����в���һ����ȫƥ��ĵ���.
    public boolean has(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        DICTTreeNode node = root;
        char[] letters = str.toCharArray();
        for (int i = 0, len = str.length(); i < len; i++) {
            int pos = letters[i] - 'a';
            if (node.son[pos] != null) {
                node = node.son[pos];
            } else {
                return false;
            }
        }
        return node.isEnd;
    }

    // ǰ������ֵ���.
    public void preTraverse(DICTTreeNode node) {
        if (node != null) {
            System.out.print(node.val + "-");
            for (DICTTreeNode child : node.son) {
                preTraverse(child);
            }
        }
    }

    public DICTTreeNode getRoot() {
        return this.root;
    }

    public static void main(String[] args) {
        DICTTree tree = new DICTTree();
        String[] strs = {"banana", "band", "bee", "absolute", "acm",};
        String[] prefix = {"ba", "b", "band", "abc",};
        for (String str : strs) {
            tree.insert(str);
        }
        System.out.println(tree.has("abc"));
        tree.preTraverse(tree.getRoot());
        System.out.println();
        // tree.print AllWords();
        for (String pre : prefix) {
            int num = tree.countPrefix(pre);
            System.out.println(pre + "" + num);
        }
    }
}