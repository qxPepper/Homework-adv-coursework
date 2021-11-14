package ru.netology.server;

import ru.netology.Logger;

import java.io.*;
import java.net.Socket;

public class ClientConnect implements Runnable {
    Logger logger = Logger.getInstance();
    private final Server server;
    private BufferedReader input;
    private PrintWriter output;
    private String name;

    public ClientConnect(Socket clientSocket, Server server) {
        this.server = server;

        try {
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            output = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);
            name = input.readLine();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void run() {
        String message = "";
        try {
            // ввод имени
            message = name + " присоединился к чату. Количество персон в чате: " +
                    server.clients.size() + ". Выход из чата /exit.";
            logger.log("Сервер", name + " присоединился к чату. Количество персон в чате: " +
                    server.clients.size());

            System.out.println(message);

            server.sendToChat(message);

            // чат
            while (true) {
                message = input.readLine();

                if (message.equals("/exit")) {
                    break;
                }
                message = "[" + name + "] " + message;
                logger.log("Сервер", message);

                server.sendToChat(message);
            }

            //выход из чата
            sendMessage(message);

            server.remoteFromChat(this);


            if (server.clients.size() == 0) {
                server.getServerSocket().close();
                return;
            }

            message = name + " вышел из чата. Количество персон в чате: " +
                    server.clients.size() + ". Выход из чата /exit.";
            logger.log("Сервер", name + " вышел из чата. Количество персон в чате: " +
                    server.clients.size());

            server.sendToChat(message);

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        output.println(message);
    }
}

