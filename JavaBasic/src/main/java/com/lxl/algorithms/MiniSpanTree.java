package com.lxl.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * ͼ����С�������㷨
 *
 * @author win7
 */
public class MiniSpanTree {
    /**
     * ��ͼ��С��������PRIM�㷨
     * ����˼�룺����N=(V,{E})����ͨ����TE��N�ϵ������������еı�ü��ϡ��㷨��U={u0}(u0����V)��
     * TE={}��ʼ���ظ�ִ�����������������е�u����U��v����V-U�ıߣ�u��v������E���ҵ�һ��������С
     * �ıߣ�u0��v0�����뼯��TE��ͬ��v0����U��ֱ��U=VΪֹ����ʱTE�б���n-1���ߣ���T=(V,{TE})
     * ΪN����С��������
     *
     * @param graph ͼ
     * @param start ��ʼ�ڵ�
     * @param n     ͼ�нڵ���
     */
    public static void PRIM(int[][] graph, int start, int n) {
        int[][] mins = new int[n][2];//���ڱ��漯��U��V-U֮�����С�ߺ�����ֵ��mins[i][0]ֵ��ʾ���ýڵ�i�ߵ���ʼ�ڵ�
        //ֵΪ-1��ʾû�е�������ʼ�㣬mins[i][1]ֵ��ʾ���ñߵ���Сֵ��
        //mins[i][1]=0��ʾ�ýڵ��ѽ��ڼ���U��
        for (int i = 0; i < n; i++) {//��ʼ��mins

            if (i == start) {
                mins[i][0] = -1;
                mins[i][1] = 0;
            } else if (graph[start][i] != -1) {//˵�����ڣ�start��i���ı�
                mins[i][0] = start;
                mins[i][1] = graph[start][i];
            } else {
                mins[i][0] = -1;
                mins[i][1] = Integer.MAX_VALUE;
            }
//			System.out.println("mins["+i+"][0]="+mins[i][0]+"||mins["+i+"][1]="+mins[i][1]);
        }
        for (int i = 0; i < n - 1; i++) {
            int minV = -1, minW = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {//�ҵ�mins����Сֵ��ʹ��O(n^2)ʱ��

                if (mins[j][1] != 0 && minW > mins[j][1]) {
                    minW = mins[j][1];
                    minV = j;
                }
            }
//			System.out.println("minV="+minV);
            mins[minV][1] = 0;
            System.out.println("��С�������ĵ�" + i + "����С��=<" + (mins[minV][0] + 1) + "," + (minV + 1) + ">��Ȩ��=" + minW);
            for (int j = 0; j < n; j++) {//����mins����
                if (mins[j][1] != 0) {
//					System.out.println("MINV="+minV+"||tree[minV][j]="+tree[minV][j]);
                    if (graph[minV][j] != -1 && graph[minV][j] < mins[j][1]) {
                        mins[j][0] = minV;
                        mins[j][1] = graph[minV][j];
                    }
                }
            }
        }
    }

    /**
     * ����С����Kruskal�㷨
     * �㷨˼�룺��³˹�����㷨����һ��;�������е���С��������������ͨ��N=(V,{E})������
     * ��С�������ĳ�ʦ״̬Ϊֻ��n��������ޱߵķ���ͨͼT=(V,{})��;��ÿ�������Գ�һ����ͨ������
     * ��E��ѡ�������С�ıߣ����ñ��·��Ķ�������T�в�ͬ����ͨ�����ϣ��򽫴˱߼��뵽T�У�������ȥ�˱�
     * ��ѡ����һ����С�ıߡ��Դ����ƣ�ֱ��T�����еĶ��㶼��ͬһ��ͨ������Ϊֹ��
     *
     * @param V ͼ�еĽڵ㼯��
     * @param E ͼ�бߵļ���
     */
    public static void KRUSKAL(int[] V, Edge[] E) {
        Arrays.sort(E);//���߰���Ȩ��w��������
        ArrayList<HashSet> sets = new ArrayList<HashSet>();
        for (int i = 0; i < V.length; i++) {
            HashSet set = new HashSet();
            set.add(V[i]);
            sets.add(set);
        }

        System.out.println("++++++++++++++++++++++size=" + sets.size());
        for (int i = 0; i < E.length; i++) {
            int start = E[i].i, end = E[i].j;
            int counti = -1, countj = -2;
            for (int j = 0; j < sets.size(); j++) {
                HashSet set = sets.get(j);
                if (set.contains(start)) {
                    counti = j;
                }

                if (set.contains(end)) {
                    countj = j;
                }
            }
            if (counti < 0 || countj < 0) System.err.println("û�����������ҵ��ڵ㣬����");

            if (counti != countj) {
                System.out.println("���start=" + start + "||end=" + end + "||w=" + E[i].w);
                HashSet setj = sets.get(countj);
                sets.remove(countj);
                HashSet seti = sets.get(counti);
                sets.remove(counti);
                seti.addAll(setj);
                sets.add(seti);
            } else {
                System.out.println("������һ�������У��������start=" + start + "||end=" + end + "||w=" + E[i].w);

            }
        }


    }

    public static void main(String[] args) {
        int[][] tree = {
                {-1, 6, 1, 5, -1, -1},
                {6, -1, 5, -1, 3, -1},
                {1, 5, -1, 5, 6, 4},
                {5, -1, 5, -1, -1, 2},
                {-1, 3, 6, -1, -1, 6},
                {-1, -1, 4, 2, 6, -1}
        };
        MiniSpanTree.PRIM(tree, 0, 6);
        System.out.println("+++++++++++++++++++++++++++++++++");

        int[] V = {1, 2, 3, 4, 5, 6};
        Edge[] E = new Edge[10];
        E[0] = new Edge(1, 2, 6);
        E[1] = new Edge(1, 3, 1);
        E[2] = new Edge(1, 4, 5);
        E[3] = new Edge(2, 3, 5);
        E[4] = new Edge(2, 5, 3);
        E[5] = new Edge(3, 4, 5);
        E[6] = new Edge(3, 5, 6);
        E[7] = new Edge(3, 6, 4);
        E[8] = new Edge(4, 6, 2);
        E[9] = new Edge(5, 6, 6);
        MiniSpanTree.KRUSKAL(V, E);
    }

}
