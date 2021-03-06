package com.lxl.algorithms;

/**
 * Dijkstra(�Ͻ�˹����)�㷨�ǵ��͵ĵ�Դ���·���㷨�����ڼ���һ���ڵ㵽�������нڵ�
 * �����·������Ҫ�ص�������ʼ��Ϊ������������չ��ֱ����չ���յ�Ϊֹ��Dijkstra
 * �㷨�Ǻ��д����Ե����·���㷨���ںܶ�רҵ�γ��ж���Ϊ������������ϸ�Ľ��ܣ������ݽṹ�� ͼ�ۣ��˳�ѧ�ȵȡ�ע����㷨Ҫ��ͼ�в����ڸ�Ȩ�ߡ�
 * <p>
 * .�㷨����
 * <p>
 * 1)�㷨˼�룺��G=(V,E)��һ����Ȩ����ͼ����ͼ�ж��㼯��V�ֳ����飬��һ��Ϊ��������·���Ķ��㼯�ϣ���S��ʾ����ʼʱS��ֻ��һ��Դ�㣬
 * �Ժ�ÿ���һ�����·�� , �ͽ����뵽����S�У�ֱ��ȫ�����㶼���뵽S�У��㷨�ͽ����ˣ����ڶ���Ϊ����δȷ�����·���Ķ��㼯�ϣ���U��ʾ����
 * �����·�����ȵĵ����������ΰѵڶ���Ķ������S��
 * ���ڼ���Ĺ����У��ܱ��ִ�Դ��v��S�и���������·�����Ȳ����ڴ�Դ��v��U���κζ�������·�����ȡ�����
 * ��ÿ�������Ӧһ�����룬S�еĶ���ľ�����Ǵ�v���˶�������·�����ȣ�U�еĶ���ľ��룬�Ǵ�v���˶���ֻ����S�еĶ���Ϊ�м䶥��ĵ�ǰ���·�����ȡ�
 * <p>
 * 2)�㷨���裺
 * <p>
 * a.��ʼʱ��Sֻ����Դ�㣬��S��{v}��v�ľ���Ϊ0��U������v����������㣬��:U={���ඥ��}����v��U�ж���u�бߣ���<u,v>������Ȩֵ��
 * ��u����v�ĳ����ڽӵ㣬��<u,v>ȨֵΪ�ޡ�
 * <p>
 * b.��U��ѡȡһ������v��С�Ķ���k����k������S�У���ѡ���ľ������v��k�����·�����ȣ���
 * <p>
 * c.��kΪ�¿��ǵ��м�㣬�޸�U�и�����ľ��룻����Դ��v������u�ľ��루��������k����ԭ�����루����������k���̣����޸Ķ���u�ľ���ֵ��
 * �޸ĺ�ľ���ֵ�Ķ���k�ľ�����ϱ��ϵ�Ȩ��
 * <p>
 * d.�ظ�����b��cֱ�����ж��㶼������S�С�
 * �㷨ʱ�临�Ӷ�:O(n3)
 */
public class Dijkstra {

}
