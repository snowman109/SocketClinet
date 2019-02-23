package com.tao.view;

import com.tao.code.Client;
import com.tao.code.MType;
import com.tao.code.Message;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {
    private JPanel jp;
    private JButton jb1, jb2;
    private JTextField jtf;
    // 大的文本输入框
    private JTextArea jta;
    private Client client;

    public Main(Client client) {
        this.client = client;
        jp = new JPanel();

        jb1 = new JButton("发送");
        jb1.setActionCommand("send");
        jb1.addActionListener(this);
        jb2 = new JButton("关闭");
        jb2.setActionCommand("close");
        jb2.addActionListener(this);
        jtf = new JTextField(10);
        jta = new JTextArea();
        jta.setEditable(false);
        jp.add(jtf);
        jp.add(jb1);
        jp.add(jb2);
        this.add(jta, "Center");
        this.add(jp, "South");
        this.setTitle(client.getUsername());
        this.setSize(300, 400);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }

    public void showMessage(Message message) {
        if (message.getType().equals(MType.MESSAGE)) {
            jta.append(message.getMessage() + "\n");
        } else {
            jta.append("系统通知" + message.getMessage() + "\n");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("send")) {
            // 对发送的消息变形
            String text = client.getUsername() + ":" + jtf.getText();
            // 追加
            jta.append(text + "\n");
            jtf.setText("");
            client.send(new Message(MType.MESSAGE, text));
        } else if (e.getActionCommand().equals("close")) {
            client.send(new Message(MType.LOGOUT, client.getUsername() + "退出"));
            client.close();
            this.dispose();
        }
    }
}
