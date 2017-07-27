package com.lxl.algorithms.recursion;

/**
 * 把两个有序数组合成一个
 *
 * @author lxl
 */
public class MergeAppM {
    //把有序数组ab合成c
    public static void merge(int[] arrayA, int[] arrayB, int[] arrayC) {
        int sizeA = arrayA.length;
        int sizeB = arrayB.length;
        int sizeC = arrayC.length;
        int aDex = 0;
        int bDex = 0;
        int cDex = 0;

        while (aDex < sizeA && bDex < sizeB) {
            if (arrayA[aDex] < arrayB[bDex]) {
                arrayC[cDex++] = arrayA[aDex++];
            } else {
                arrayC[cDex++] = arrayB[bDex++];
            }
        }
        //arrayB 空
        while (aDex < sizeA) {
            arrayC[cDex++] = arrayA[aDex++];
        }
        //arrayA 空
        while (bDex < sizeB) {
            arrayC[cDex++] = arrayB[bDex++];
        }
    }

    public static void display(int[] theArray, int size) {
        for (int j = 0; j < size; j++) {
            System.out.print(theArray[j] + " ");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
