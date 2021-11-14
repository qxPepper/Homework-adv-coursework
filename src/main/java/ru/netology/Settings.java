package ru.netology;

import java.io.*;
import java.util.Scanner;

public class Settings {
    private final static String FILE_NAME = "settings.txt";
    private int serverPort;

    public Settings() {
        serverPort = 0;
    }

    public void setPort() {
        FileWriter fileSettings = null;

        try {
            fileSettings = new FileWriter(FILE_NAME, false);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        System.out.println("Настройки.");
        System.out.print("Введите номер серверного порта (Server port): ");
        Scanner scanner = new Scanner(System.in);

        serverPort = Integer.parseInt(scanner.nextLine());

        try {
            fileSettings.write("Server port: " + serverPort + "\n");
            fileSettings.flush();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public int getPort() {
        String line = "";
        try {
            File file = new File(FILE_NAME);
            BufferedReader reader = new BufferedReader(new FileReader(file));

            line = reader.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] result = line.split(":");

        serverPort = Integer.parseInt(result[1].trim());
        return serverPort;
    }
}
