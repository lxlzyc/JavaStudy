package com.lxl.algorithms;

/**
 * Floyd-Warshall�㷨��Floyd-Warshall algorithm���ǽ���������������·����һ���㷨��
 * ������ȷ��������ͼ��Ȩ�����·�����⣬ͬʱҲ�����ڼ�������ͼ�Ĵ��ݱհ���
 * Floyd-Warshall�㷨��ʱ�临�Ӷ�ΪO(N3)���ռ临�Ӷ�ΪO(N2)��
 * Floyd�㷨��һ������Ķ�̬�滮�㷨����ͨ�׵������������Ļ����������ǵ�Ŀ����Ѱ�Ҵӵ�i����j�����·��
 * ���Ӷ�̬�滮�ĽǶȿ����⣬������ҪΪ���Ŀ��������һ��ڹ�ͣ����ڹ�����Ƕ�̬�滮��������ľ������ڣ�
 * <p>
 * ������ڵ�i������ڵ�j�����·�������2�ֿ��ܣ�1��ֱ�Ӵ�i��j��2�Ǵ�i�������ɸ��ڵ�k��j�����ԣ����Ǽ���Dis(i,j)
 * Ϊ�ڵ�u���ڵ�v�����·���ľ��룬����ÿһ���ڵ�k�����Ǽ��Dis(i,k) + Dis(k,j) <
 * Dis(i,j)�Ƿ���������������֤����i��k�ٵ�j��·����iֱ�ӵ�j��·���̣����Ǳ�����Dis(i,j) = Dis(i,k) +
 * Dis(k,j)������һ���������Ǳ��������нڵ�k��Dis(i,j)�м�¼�ı���i��j�����·���ľ��롣
 * <p>
 * 2).�㷨������
 * <p>
 * a.������һ������·����ʼ����������֮��ľ����Ǳߵ�Ȩ���������֮��û�б���������ȨΪ�����
 * <p>
 * b.����ÿһ�Զ��� u �� v�������Ƿ����һ������ w ʹ�ô� u �� w �ٵ� v �ȼ�֪��·�����̡�����Ǹ�������
 * <p>
 * 3).Floyd�㷨���̾���ļ���----ʮ�ֽ��淨
 */
public class Floyd {

}
