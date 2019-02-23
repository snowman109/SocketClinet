package com.tao.code;

import com.tao.view.Main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private String url = "127.0.0.1";
    private static final Integer PORT = 9977;
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private String username;
    private Main main;

    public String getUsername() {
        return username;
    }

    public boolean init(String username) {
        if (username == null || username.equals("")) {
            return false;
        }
        try {
            this.username = username;
            socket = new Socket(url, PORT);
            oos = new ObjectOutputStream(socket.getOutputStream());
            // 发送自己的用户名
            oos.writeObject(new Message(MType.ID, username));
            ois = new ObjectInputStream(socket.getInputStream());
            // 能否登陆成功
            Message message = (Message) ois.readObject();
            return !message.getType().equals(MType.FALSE);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Client(String url) {
        System.out.println(url);
        if (url != null) {
            String[] part = url.split(".");
            if (part.length != 4) {
                this.url = url;
            }
        }
        System.out.println(this.url);
    }

    public void start() {
        main = new Main(this);
        Reader reader = new Reader();
        reader.start();
    }

    public void send(Message message) {
        try {
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (oos != null) {
                oos.close();
            }
            if (ois != null) {
                ois.close();
            }
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private class Reader extends Thread {
        @Override
        public void run() {
            try {
                while (!socket.isClosed()) {
                    ois = new ObjectInputStream(socket.getInputStream());
                    Message message = (Message) ois.readObject();
                    main.showMessage(message);
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}