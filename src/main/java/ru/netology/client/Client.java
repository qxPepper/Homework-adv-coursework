package ru.netology.client;

import ru.netology.Logger;
import ru.netology.Settings;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    Logger logger = Logger.getInstance();
    Settings settings = new Settings();
    private final static String SERVER_ID = "127.0.0.1";
    private Scanner scanner;
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    private String message;
    private String name;
    private boolean init;

    public Client() {
        int serverPort = settings.getPort();

        try {
            socket = new Socket(SERVER_ID, serverPort);
            scanner = new Scanner(System.in);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            init = true;

        } catch (IOException exception) {
            exception.printStackTrace();
        }

        new Thread(new OutMessage()).start();

        new Thread(new InMessage()).start();
    }

    private class OutMessage implements Runnable {
        @Override
        public void run() {
            while (true) {
                if (init) {
                    init = false;
                    System.out.print("Введите своё имя: ");
                    name = scanner.nextLine();
                    message = name;
                } else {
                    message = scanner.nextLine();
                    logger.log(name, message);
                }

                if (message.equals("/exit")) {
                    output.println(message);
                    break;
                }

                output.println(message);
            }
        }
    }

    private class InMessage implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    while ((message = input.readLine()) != null) {
                        if (message.equals("/exit")) {
                            socket.close();
                            return;
                        }
                        System.out.println(message);
                    }
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }
}
