package org.example;

import org.example.models.User;

import java.util.Scanner;

public class Menu {
    private static final Game game = new Game();
    private static final Scanner scanner = new Scanner(System.in);

    public static void mainMenu() {

        do {
            System.out.println("1. Continue");
            System.out.println("2. Start New Game");
            System.out.println("3. Join Server");
            System.out.println("4. Exit");
            System.out.print("-> Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.println("\ta. Enter your username");
                    String user = scanner.next();
                    System.out.println("\tb. Enter your password");
                    String pass = scanner.next();
                    game.continueGame(loginMenu(user, pass));
                    // TODO: 6/16/2023 complete this
                }
                case 2 -> {
                    System.out.println("\ta. Enter your username");
                    String user = scanner.next();
                    System.out.println("\tb. Enter your password");
                    String pass = scanner.next();
                    game.startGame(loginMenu(user, pass));
                    // TODO: 6/16/2023 creat in database?
                }
                case 3 -> {
                    System.out.println("\ta. Enter server IP address");
                    String ip = scanner.next();
                    System.out.println("\tb. Enter server port");
                    int port = scanner.nextInt();
                    game.joinServer(ip, port);
                    // TODO: 6/16/2023 complete socket
                }
                case 4 -> System.exit(0);
                default -> System.out.println("Invalid choice!");
            }
        } while (true);
    }

    public static User loginMenu(String username, String password) {
//       get user info : username, password
        // TODO: 6/16/2023 fix this
        return new User(username, password);
    }

    // deleted:
//    private static void joinServer(){
//        System.out.print("Enter Server Ip Address :");
//        String ip = scanner.next();
//        System.out.print("Enter Server Port :");
//        int port = scanner.nextInt();
//        game.joinServer(ip,port);
//    }

    // deleted:
//    public static void showMenu() {
//        mainMenu();
//        String next = scanner.next();
//        if (next.equals("1")) {
//            game.continueGame(loginMenu());
//        } else if (next.equals("2")) {
//            game.startGame(loginMenu());
//        } else if (next.equals("3")) {
//            joinServer();
//        } else if (next.equals("4"))
//            System.exit(0);
//    }

    public static void main(String[] args) {
        Database.read();
        mainMenu();
    }
}