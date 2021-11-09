package ru.netology.server;

import java.io.*;
import java.net.Socket;

public class ClientConnect implements Runnable {
    private final Server server;
    private static int countClients = 0;
    private BufferedReader input;
    private PrintWriter output;
    private String name;

    public ClientConnect(Socket clientSocket, Server server) {
        countClients++;
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
            while (true) {
                message = input.readLine();

                System.out.println(message);

                if (message.equals("/exit")) {
                    System.out.println("удаляем " + name);

                    exitFromChat();
                    break;
                }
                server.sendToChat(message);
            }

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void sendMessage(String message) {
        output.println(message);
        output.flush();
    }

    public void exitFromChat() {
        server.remoteFromChat(this);
        countClients--;
    }
}
