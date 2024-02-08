package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public interface ServerView {
    List<String> readFile(File file);

    void writeFile(List<String> lst, File file, String message);

    ArrayList<Client> getClients();

    void serverStartInfo();

    void serverStopInfo();
}
