package com.lxl.ServerSocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * �򵥷�������������ͻ��˷�����Ϣ ���������������󣬵ȴ��ͻ������ӵ����Ķ˿� ÿ��������������ϵ�ִ����������ѭ������
 * 1��ͨ�������������ӿͻ��˽���һ������ 2��ͨ��ĳ�ַ�ʽ��ȡ��Ϣ 3��ͨ��������������ͻ��˷�����Ϣ ClassName:
 * ServerSocketTest
 *
 * @author lxl
 * @Description: TODO
 * @date 2016-4-21
 */
public class ServerSocketTest {
    public static void main(String[] args) {
        try {
            // ServerSocket ����һ���׽��֣��˴�����һ�������ض˿�8189�ķ�����
            ServerSocket s = new ServerSocket(8189);
            // ���������ڸ��߳���ͣ�صȴ���ֱ���пͻ������ӵ�����˿�
            Socket incoming = s.accept();
            try {
                // ���������͸��������������������Ϣ�����Ϊ�ͻ��˳�������룬
                // ͬʱ���Կͻ��˳��������������ᱻ�����ڷ���������������
                InputStream inStream = incoming.getInputStream();
                OutputStream outStream = incoming.getOutputStream();
                // ��������ת��Ϊɨ�������������ת��Ϊ��д��
                Scanner in = new Scanner(inStream);
                PrintWriter out = new PrintWriter(outStream, true/* autoFlush */);
                // �˴��뽫���ͻ��˷���һ����Ϣ
                out.print("Hello!Enter BYE toexit");

                boolean done = false;
                // ��������ȡ�ͻ��˵����룬�������һ��
                while (!done && in.hasNextLine()) {
                    String line = in.nextLine();
                    out.println("Echo: " + line);
                    if ("BYE".equals(line.trim())) {
                        done = true;
                    }
                }
            } finally {
                // �ر����ӽ������׽���
                incoming.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
