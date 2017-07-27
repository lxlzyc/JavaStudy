package com.lxl.algorithms;

import java.util.Arrays;

public class MergeSort {
    /**
     * �ϲ������ǽ����ڹ鲢�����ϵ�һ����Ч�������㷨�����㷨�ǲ��÷��η���Divide and Conquer����һ���ǳ����͵�Ӧ�á�
     * <p>
     * �ϲ������ǽ����������������ϣ������ϲ���һ���µ���������Ѵ��������з�Ϊ���ɸ������У�ÿ��������������ġ�
     * Ȼ���ٰ����������кϲ�Ϊ�����������С�
     * <p>
     * ��������������кϲ����õ���ȫ��������У�����ʹÿ��������������ʹ�����жμ������������������ϲ���һ���������Ϊ2-·�鲢��
     * �ϲ�����Ҳ�й鲢���� ���Ӷ� �ʱ�临�Ӷ� ���ʱ�临�Ӷ� �ռ临�Ӷ� �������������
     *
     * @param array
     */
    public static void mergeSort(int[] array) {

        int length = array.length;
        int middle = length / 2;

        if (length > 1) {

            int[] left = Arrays.copyOfRange(array, 0, middle);// ��������array����벿��
            int[] right = Arrays.copyOfRange(array, middle, length);// ��������array���Ұ벿��
            mergeSort(left);// �ݹ�array����벿��
            mergeSort(right);// �ݹ�array���Ұ벿��
            merge(array, left, right);// ������벿�֡��Ұ벿�ֺϲ���Array

        }
    }

    // �ϲ����飬����
    private static void merge(int[] result, int[] left, int[] right) {

        int i = 0, l = 0, r = 0;

        while (l < left.length && r < right.length) {

            if (left[l] < right[r]) {
                result[i] = left[l];
                i++;
                l++;
            } else {
                result[i] = right[r];
                i++;
                r++;
            }
        }

        while (r < right.length) {// ����ұ�ʣ�ºϲ��ұߵ�
            result[i] = right[r];
            r++;
            i++;
        }

        while (l < left.length) {
            result[i] = left[l];
            l++;
            i++;
        }
    }
}
