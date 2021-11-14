package ru.netology.server;

import ru.netology.Logger;
import ru.netology.Settings;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    Logger logger = Logger.getInstance();
    Settings settings = new Settings();
    List<ClientConnect> clients = new ArrayList<>();
    private ServerSocket serverSocket;
    private Socket clientSocket;

    public Server() {
        int serverPort = getPort();
        try {
            serverSocket = new ServerSocket(serverPort);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        chat();
    }

    public void chat() {
        try {
            System.out.println("Чат открыт!");
            logger.log("Главный сервер", "Чат открыт!");

            while (true) {
                clientSocket = serverSocket.accept();

                ClientConnect clientConnect = new ClientConnect(clientSocket, this);
                clients.add(clientConnect);
                new Thread(clientConnect).start();
            }

        } catch (IOException exception) {
            System.out.println("Вышли из цикла!");
            logger.log("Главный сервер", "Вышли из цикла!");
        } finally {
            try {
                System.out.println("Чат закрыт!");
                logger.log("Главный сервер", "Чат закрыт!");
                serverSocket.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    public int getPort() {
        settings.setPort();
        return settings.getPort();
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public void sendToChat(String message) {
        for (ClientConnect element : clients) {
            element.sendMessage(message);
        }
    }

    public void remoteFromChat(ClientConnect client) {
        clients.remove(client);
    }
}

