package com.lxl.socket;

import javax.swing.JFrame;

public class InterruptibleSocketTest {
    public static void main(String[] args) {
        JFrame frame = new InterruptibleSocketFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
