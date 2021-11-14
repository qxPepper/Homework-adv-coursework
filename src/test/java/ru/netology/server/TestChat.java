package ru.netology.server;

import org.junit.jupiter.api.Assertions;
import org.junit.Test;

import ru.netology.client.Client;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TestChat {
    @Test
    public void testMainServerConnection() {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        try {
            serverSocket = new ServerSocket(34343);

            Client client = new Client();
            clientSocket = serverSocket.accept();

        } catch (IOException exception) {
            exception.printStackTrace();
        }

        int expectedServerPort = 34343;
        int resultServerPort = serverSocket.getLocalPort();
        Assertions.assertEquals(expectedServerPort, resultServerPort);

        String expectedAddress = "/127.0.0.1";
        String resultAddress = String.valueOf(clientSocket.getLocalAddress());
        Assertions.assertEquals(expectedAddress, resultAddress);

        int expectedPort = 34343;
        int resultPort = clientSocket.getLocalPort();
        Assertions.assertEquals(expectedPort, resultPort);
    }
}
