package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ServerGUI extends JFrame {
    private static final int POS_X = 200;
    private static final int POS_Y = 150;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private JTextArea statusField;
    public final JButton btnStart = new JButton("Запустить сервер");
    public final JButton btnStop = new JButton("Остановить сервер");
    public boolean isServerWorking;
    public File messagesLog = new File("./src/main/java/org/example/chat_log.txt");

    private void setWindowProperties() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Сервер чата");
        setAlwaysOnTop(true);
        statusField = new JTextArea();
        add(statusField, BorderLayout.SOUTH);
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setLayout(new GridLayout(1, 2));
        mainPanel.add(btnStart);
        mainPanel.add(btnStop);
        add(mainPanel);
        setVisible(true);
    }

    public ServerGUI(ServerView serverView) {
        isServerWorking = false;
        setWindowProperties();
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statusField.setText("Сервер запущен");
                isServerWorking = true;
                serverView.serverStartInfo();
                serverView.writeFile(serverView.readFile(messagesLog), messagesLog, "Чат доступен\n");
            }
        });
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statusField.setText("Сервер остановлен");
                isServerWorking = false;
                serverView.serverStopInfo();
                serverView.writeFile(serverView.readFile(messagesLog), messagesLog, "Чат временно недоступен из-за работ на сервере\n" +
                        "Пользователи вышли из чата\n");
            }
        });
    }
}
