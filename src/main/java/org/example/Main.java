package org.example;

public class Main {
    public static void main(String[] args) {
        Server server = new Server();
        ServerGUI serverGUI = new ServerGUI(server);
        new Client(server, serverGUI, 700, 500);
        new Client(server, serverGUI, 700, 150);
        new Client(server, serverGUI, 200, 500);
    }
}
