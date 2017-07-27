package com.lxl.algorithms.recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 递归实现输入一个字符串，显示此字符串的所有不同排列
 *
 * @author lxl
 */
public class AnagramAppM {

    static int size;
    static int count;
    static char[] arrChar = new char[100];

    public static void doAnagram(int newSize) {
        if (newSize == 1) {
            return;
        }
        for (int j = 0; j < newSize; j++) {
            doAnagram(newSize - 1);
            if (newSize == 2) {
                displayWord();
            }
            rotate(newSize);
        }
    }

    private static void rotate(int newSize) {
        int j;
        int position = size - newSize;
        char temp = arrChar[position];
        for (j = position + 1; j < size; j++) {
            arrChar[j - 1] = arrChar[j];
        }
        arrChar[j - 1] = temp;
    }


    private static void displayWord() {
        if (count < 99) {
            System.out.print("  ");
        }
        if (count < 9) {
            System.out.print("  ");
        }
        System.out.print((++count) + "  ");
        for (int j = 0; j < size; j++) {
            System.out.print(arrChar[j]);
        }
        System.out.print("  ");
        if (count % 6 == 0) {
            System.out.println("");
        }
    }

    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }

    public static void main(String[] args) throws IOException {
        System.out.println("输入字符串");
        String input = getString();
        size = input.length();
        count = 0;
        for (int j = 0; j < size; j++) {
            arrChar[j] = input.charAt(j);
        }
        doAnagram(size);
    }

}
