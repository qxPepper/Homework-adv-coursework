package ru.netology.server;

import ru.netology.Logger;
import ru.netology.Settings;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static int SERVER_PORT = 23444;
    List<ClientConnect> clients = new ArrayList<>();
    Logger logger = Logger.getInstance();
    Settings settings = new Settings();

    public Server() {
        Socket clientSocket = null;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Чат открыт!");
            logger.log("", "Чат открыт!");

            while (true) {
                clientSocket = serverSocket.accept();
                ClientConnect client = new ClientConnect(clientSocket, this);
                clients.add(client);

                String message = client.getName() +
                        " присоединился к чату. Количество персон в чате увеличилось до: " +
                        clients.size() + ". Выход из чата /exit.";
                sendToChat(message);

                new Thread(client).start();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            try {
                clientSocket.close();
                serverSocket.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void sendToChat(String message) {
        for (ClientConnect element : clients) {
            element.sendMessage(message);
        }
    }

    public void remoteFromChat(ClientConnect client) {
        clients.remove(client);
        System.out.println(client.getName() + " вышел из чата. Количество персон в чате уменьшилось до: " +
                clients.size());
        sendToChat(client.getName() + " вышел из чата. Количество персон в чате уменьшилось до: " +
                clients.size());
        logger.log(client.getName(),  " вышел из чата. Количество персон в чате уменьшилось до: " +
                clients.size());
    }
}














//package ru.netology.server;
//
//import ru.netology.Logger;
//
//import java.io.*;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Server {
//    private static final int SERVER_PORT = 23444;
//    List<ClientConnect> clients = new ArrayList<>();
//    Logger logger = Logger.getInstance();
//
//
//
//
//    public static void main(String[] args) {
//        Server server = new Server();
//    }
//
//    public Server() {
//        Socket clientSocket = null;
//        ServerSocket serverSocket = null;
//        try {
//            serverSocket = new ServerSocket(SERVER_PORT);
//            System.out.println("Чат открыт!");
//            logger.log("", "Чат открыт!");
//
//            while (true) {
//                clientSocket = serverSocket.accept();
//                ClientConnect client = new ClientConnect(clientSocket, this);
//                clients.add(client);
//
////                String message = client.getName() +
////                        " присоединился к чату. Количество персон в чате увеличилось до: " +
////                        clients.size() + ". Выход из чата /exit.";
////                sendToChat(message);
//
//                new Thread(client).start();
//            }
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        } finally {
//            try {
//                clientSocket.close();
//                serverSocket.close();
//            } catch (IOException exception) {
//                exception.printStackTrace();
//            }
//        }
//    }
//
//    public void sendToChat(String message) {
//        for (ClientConnect element : clients) {
//            System.out.println(element.getName() + " - " + message);
//            element.sendMessage(message);
//        }
//    }
//
//    public void remoteFromChat(ClientConnect client) {
//        clients.remove(client);
//        System.out.println(client.getName() + " вышел из чата. Количество персон в чате уменьшилось до: " +
//                clients.size());
//        sendToChat(client.getName() + " вышел из чата. Количество персон в чате уменьшилось до: " +
//                clients.size());
//    }
//}
























//package ru.netology.server;
//
//import ru.netology.Logger;
//
//import java.io.*;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Server {
//    private static final int SERVER_PORT = 23444;
//    List<ClientConnect> clients = new ArrayList<>();
//    Logger logger = Logger.getInstance();
//
//    public static void main(String[] args) {
//        Server server = new Server();
//    }
//
//    public Server() {
//        Socket clientSocket = null;
//        ServerSocket serverSocket = null;
//        try {
//            serverSocket = new ServerSocket(SERVER_PORT);
//            System.out.println("Чат открыт!");
//            logger.log("", "Чат открыт!");
//
//            while (true) {
//                clientSocket = serverSocket.accept();
//                ClientConnect client = new ClientConnect(clientSocket, this);
//                clients.add(client);
//
////                String message = client.getName() +
////                        " присоединился к чату. Количество персон в чате увеличилось до: " +
////                        clients.size() + ". Выход из чата /exit.";
////                sendToChat(message);
////
////                new Thread(client).start();
//            }
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        } finally {
//            try {
//                clientSocket.close();
//                serverSocket.close();
//            } catch (IOException exception) {
//                exception.printStackTrace();
//            }
//        }
//    }
//
//    public void sendToChat(String message) {
////        for (ClientConnect element : clients) {
////            element.sendMessage(message);
////        }
//
//        for (int i = 0; i < clients.size(); i++) {
//            System.out.println(clients.get(i).getName() + " !!! " + message);
//            clients.get(i).sendMessage(message);
//        }
//    }
//
//    public void remoteFromChat(ClientConnect client) {
//        clients.remove(client);
//        System.out.println(client.getName() + " вышел из чата. Количество персон в чате уменьшилось до: " +
//                clients.size());
//        sendToChat(client.getName() + " вышел из чата. Количество персон в чате уменьшилось до: " +
//                clients.size());
//    }
//}
