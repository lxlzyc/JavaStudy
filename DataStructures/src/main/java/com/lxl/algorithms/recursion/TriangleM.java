package com.lxl.algorithms.recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 毕达哥拉斯三角数
 * 第n项是由第n-1项加n得到
 * 递归实现
 *
 * @author lxl
 */
public class TriangleM {

    static int theNumber;

    public static int triangle(int n) {
        if (n == 1) {
            return 1;
        } else {
            return (n + triangle(n - 1));
        }
    }

    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }

    public static int getInt() throws IOException {
        String s = getString();
        return Integer.parseInt(s);
    }

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
//		System.out.println("输入数字");
//		theNumber = getInt();
//		System.out.println(triangle(theNumber));
        if (1 == 1)
            System.out.println("1");
        System.out.println("2");
    }

}
