package ru.netology;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Settings {
    private final static String FILE_NAME = "settings.txt";
    private int serverPort;

    public Settings() {
        FileWriter fileSettings = null;
        try {
            fileSettings = new FileWriter(FILE_NAME, false);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        System.out.print("Введите номер серверного порта: ");
        Scanner scanner = new Scanner(System.in);

        serverPort = Integer.parseInt(scanner.nextLine());

        try {
            fileSettings.write("Серверный порт: " + serverPort + "\n");
            fileSettings.flush();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public int getPort() {
        return serverPort;
    }
}
