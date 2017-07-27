package com.lxl.ServerSocket;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * ����email
 * Ϊ�˷���email�����뽨��һ�����˿�25���׽������ӣ���SMTP�˿�
 * ��������
 * 1����һ�������������׽���
 * Socket s = new Socket("mail.yourserver.com",25);
 * PrintWriter out = new
 * PrintWriter(s.getOutputStream());
 * 2)���������Ϣ����ӡ��
 * <p>
 * ע��SMTP�淶��ÿһ�ж�Ҫ��\r\n����β
 * <p>
 * <p>
 * ClassName: MailTestFrame
 *
 * @author lxl
 * @Description: TODO
 * @date 2016-4-22
 */
public class MailTestFrame extends JFrame {

    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 300;

    private Scanner in;
    private PrintWriter out;
    private JTextField from;
    private JTextField to;
    private JTextField smtpServer;
    private JTextArea message;
    private JTextArea comm;

    public MailTestFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setTitle("MailTest");

        setLayout(new GridBagLayout());

        add(new JLabel("From:"), new GBC(0, 0).setFill(GBC.HORIZONTAL));
        from = new JTextField(20);
        add(from, new GBC(1, 0).setFill(GBC.HORIZONTAL).setWeight(100, 0));

        add(new JLabel("To:"), new GBC(0, 1).setFill(GBC.HORIZONTAL));
        to = new JTextField(20);
        add(to, new GBC(1, 1).setFill(GBC.HORIZONTAL).setWeight(100, 0));

        add(new JLabel("SMTP server:"), new GBC(0, 2).setFill(GBC.HORIZONTAL));
        smtpServer = new JTextField(20);
        add(smtpServer, new GBC(1, 2).setFill(GBC.HORIZONTAL).setWeight(100, 0));

        message = new JTextArea();
        add(new JScrollPane(message), new GBC(0, 3, 2, 1).setFill(GBC.BOTH)
                .setWeight(100, 100));

        comm = new JTextArea();
        add(new JScrollPane(comm), new GBC(0, 4, 2, 1).setFill(GBC.BOTH)
                .setWeight(100, 100));

        JPanel buttonPanel = new JPanel();
        add(buttonPanel, new GBC(0, 5, 2, 1));

        JButton sendButton = new JButton("send");
        buttonPanel.add(sendButton);
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                new Thread(new Runnable() {
                    public void run() {
                        comm.setText("");
                        sendMail();
                    }
                }).start();
            }
        });
    }

    public void sendMail() {
        try {
            Socket s = new Socket(smtpServer.getText(), 25);
            InputStream inStream = s.getInputStream();
            OutputStream outStream = s.getOutputStream();
            in = new Scanner(inStream);
            out = new PrintWriter(outStream, true/* autoFlush */);
            String hostName = InetAddress.getLocalHost().getHostName();

            receive();
            send("HELLO " + hostName);
            receive();
            send("MAIL FROM:<" + from.getText() + ">");
            receive();
            send("RCPT TO:<" + to.getText() + ">");
            receive();
            send("DATA");
            receive();
            send(message.getText());
            send(".");
            receive();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void send(String s) {
        comm.append(s);
        comm.append("\n");
        out.print(s.replaceAll("\n", "\r\n"));
        out.print("\r\n");
        out.flush();
    }

    private void receive() {
        if (in.hasNextLine()) {
            String line = in.nextLine();
            comm.append(line);
            comm.append("\n");
        }
    }

}
