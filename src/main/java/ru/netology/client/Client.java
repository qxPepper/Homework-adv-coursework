package ru.netology.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final static String SERVER_ID = "127.0.0.1";
    private final static int SERVER_PORT = 23444;
    private String name;

    public Client() {
        try (Socket socket = new Socket(SERVER_ID, SERVER_PORT);
             Scanner scanner = new Scanner(System.in);
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true)) {

            System.out.print("Введите своё имя: ");
            name = scanner.nextLine();
            output.println(name);
            output.flush();
            System.out.println(input.readLine());

            while (true) {
                if (input.ready()) {
                    String str;
                    while ((str = input.readLine()) != null) {
                        System.out.println(str);
                    }
                }

                System.out.print(" >>> ");
                String entry = scanner.nextLine();

                if (entry.equals("/exit")) {
                    output.println(entry);
                    output.flush();
                    break;
                }

                output.println("[" + name + "] " + entry);
                output.flush();

                System.out.println(input.readLine());
            }

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}








//package ru.netology.client;
//
//        import ru.netology.Logger;
//
//        import java.io.*;
//        import java.net.Socket;
//        import java.util.Scanner;
//
//public class Client {
//    private final static String SERVER_ID = "127.0.0.1";
//    private final static int SERVER_PORT = 23444;
//    private final Logger logger = Logger.getInstance();
//    private String message;
//
//
//    public Client(String name) {
//        try (Socket socket = new Socket(SERVER_ID, SERVER_PORT);
//             Scanner scanner = new Scanner(System.in);
//             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//             PrintWriter output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true)) {
//            boolean init = true;
//
//
//            output.println(name);
//            output.flush();
//            System.out.println(input.readLine());
//
//            while (true) {
//                if (input.ready()) {
//                    String str;
//                    while ((str = input.readLine()) != null) {
//                        System.out.println(str);
//                    }
//                }
//
//                System.out.print(" >>> ");
//                String entry = scanner.nextLine();
//
//                if (entry.equals("/exit")) {
//                    output.println(entry);
//                    output.flush();
//                    break;
//                }
//
//                output.println("[" + name + "] " + entry);
//                output.flush();
//
//                System.out.println(input.readLine());
//            }
//
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        }
//    }
//}

