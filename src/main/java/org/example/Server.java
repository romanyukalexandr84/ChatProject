package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Server implements ServerView {
    public ArrayList<Client> clients;

    public Server() {
        clients = new ArrayList<>();
    }

    @Override
    public List<String> readFile(File file) {
        List<String> lst = new ArrayList<>();
        try (FileReader fr = new FileReader(file); BufferedReader bf = new BufferedReader(fr)) {
            String line;
            while ((line = bf.readLine()) != null) {
                lst.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found" + file.getName());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return lst;
    }

    @Override
    public void writeFile(List<String> lst, File file, String message) {
        try (FileWriter fw = new FileWriter(file); BufferedWriter bf = new BufferedWriter(fw)) {
            for (String item : lst) {
                bf.write(item);
                bf.newLine();
            }
            bf.write(message);
        } catch (FileNotFoundException e) {
            System.out.println("File not found" + file.getName());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void serverStartInfo() {
        String message = "Чат доступен\n";
        for (Client client : clients) {
            client.messagesField.append(message);
        }
    }

    @Override
    public void serverStopInfo() {
        String message = "Чат временно недоступен из-за работ на сервере\n" +
                "Пользователи вышли из чата\n";
        for (Client client : clients) {
            client.isUserOnline = false;
            client.messagesField.append(message);
        }
    }

    @Override
    public ArrayList<Client> getClients() {
        return clients;
    }
}
