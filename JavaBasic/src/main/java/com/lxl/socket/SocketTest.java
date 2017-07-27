package com.lxl.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * ���ӵ�������
 * ClassName: SocketTest
 *
 * @author lxl
 * @Description: TODO
 * @date 2016-4-21
 */
public class SocketTest {
    public static void main(String[] args) {
        try {
            // ��һ���׽��� ����Զ�̵�ַ�Ͷ˿ںŴ��ݸ��׽��ֵĹ�����������ʧ���׳�UnknownHostException�쳣��
            Socket s = new Socket("time-A.timefreq.bldrdoc.gov", 13);
            try {
                // �׽��ִ򿪺󣬿��Ի��InputStream
                InputStream inStream = s.getInputStream();
                // ���InputStream�󣬳������ʹ��һ��Scanner��ȡ���������͵�ÿһ���ַ�����ÿһ�д�ӡ����׼���
                // �˹��̳���������������ҷ������Ͽ�����Ϊֹ
                Scanner in = new Scanner(inStream);
                while (in.hasNext()) {
                    String line = in.nextLine();
                    System.out.println(line);
                }
            } finally {
                s.close();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
