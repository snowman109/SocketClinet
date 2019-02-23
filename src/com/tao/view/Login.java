package com.tao.view;

import com.tao.code.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {
    private JPanel jp, jp2, jp3;
    private JTextField jtf, jtf2;
    private JButton jb;
    private JLabel jl, jl2;

    public Login() {
        // 先进性初始化，否则会出异常
        jp = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jl = new JLabel("输入用户名");
        jtf = new JTextField(16);
        jl2 = new JLabel("请输入服务器IP");
        jtf2 = new JTextField("127.0.0.1", 16);
        jb = new JButton("登陆");
        jb.setActionCommand("login");
        jb.addActionListener(this);

        // 网格布局
        jp.setLayout(new GridLayout(3, 1));
        jp2.add(jl);
        jp2.add(jtf);
        jp3.add(jl2);
        jp3.add(jtf2);
        jp.add(jp2);
        jp.add(jp3);
        jp.add(jb);

        this.add(jp);
        this.setSize(400, 200);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("login")) {
            String username = jtf.getText();
            String serverIp = jtf2.getText();
            Client client = new Client(serverIp);
            boolean success = client.init(username);
            if (!success) {
                JOptionPane.showMessageDialog(null, "登陆失败");
                client.close();
                this.dispose();
            } else {
                client.start();
                this.dispose();
            }
        }
    }

    public static void main(String[] args) {
        Login login = new Login();
    }
}
