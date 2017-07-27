package com.lxl.post;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class PostTestFrame extends JFrame {

    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 300;
    public static String[] countries = {"CHINA", "US"};


    public PostTestFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setTitle("PostTest");

        JPanel northPanel = new JPanel();
        add(northPanel, BorderLayout.NORTH);

        final JComboBox combo = new JComboBox();
        for (int i = 0, l = countries.length; i < l; i++) {
            combo.addItem(countries[i]);
        }
        northPanel.add(combo);

        final JTextArea result = new JTextArea();
        add(new JScrollPane(result));

        JButton getButton = new JButton("GET");
        northPanel.add(getButton);
        getButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Thread(new Runnable() {
                    public void run() {
                        final String SERVER_URL = "http://www.census.gov/cgi-bin/ipc/idbsprd";
                        result.setText("");
                        Map<String, String> post = new HashMap<String, String>();
                        post.put("tbl", "001");
                        post.put("cty", countries[2 * combo.getSelectedIndex() + 1]);
                        post.put("optyr", "latest checked");
                        try {
                            result.setText(doPost(SERVER_URL, post));
                        } catch (IOException e) {
                            result.setText("" + e);
                        }
                    }
                }).start();
            }
        });
    }

    public static String doPost(String urlString,
                                Map<String, String> nameValuePairs) throws IOException {
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();
        connection.setDoOutput(true);

        PrintWriter out = new PrintWriter(connection.getOutputStream());

        boolean first = true;
        for (Map.Entry pair : nameValuePairs.entrySet()) {
            if (first) {
                first = false;
            } else {
                out.print('&');
            }
            String name = (String) pair.getKey();
            String value = (String) pair.getValue();
            out.print(name);
            out.print('=');
            out.print(URLEncoder.encode(value, "UTF-8"));

        }
        out.close();
        Scanner in = null;
        StringBuilder response = new StringBuilder();
        try {
            in = new Scanner(connection.getInputStream());
        } catch (IOException e) {
            if (!(connection instanceof HttpURLConnection)) {
                throw e;
            }

        }
        while (in.hasNextLine()) {
            response.append(in.nextLine());
            response.append("\n");
        }
        in.close();
        return response.toString();
    }
}
