package com.lxl.algorithms.recursion;

/**
 * 递归解决汉诺塔问题
 *
 * @author lxl
 */
public class TowersAppM {
    //盘子数量
    static int nDisks = 5;

    public static void doTowers(int topN, char from, char inter, char to) {
        if (topN == 1) {
            System.out.println("Disk 1 from " + from + " to " + to);
        } else {
            doTowers(topN - 1, from, to, inter);// from -->inter
            System.out.println("Disk " + topN + " from " + from + " to " + to);
            doTowers(topN - 1, inter, from, to);//inter-->to
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        doTowers(nDisks, 'A', 'B', 'C');
    }

}
