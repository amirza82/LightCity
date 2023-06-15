package org.example;

import org.example.models.User;

import java.util.Scanner;

public class Menu {
    private static Game game = new Game();
    private static Scanner scanner = new Scanner(System.in);
    public static void userMenu(){
        Scanner input = new Scanner(System.in);
        int choice;
        do {
            System.out.println("1. Go to");
            System.out.println("\ta. Enter location or id or title");
            System.out.println("2. Process Location");
            System.out.println("\ta. Show where is character");
            System.out.println("\tb. Show options");
            System.out.println("3. Dashbord");
            System.out.println("\ta. My job");
            System.out.println("\t\t- find job");
            System.out.println("\tb. Properties");
            System.out.println("\t\t- Show properties");
            System.out.println("\t\t-Sell");
            System.out.println("\t\t-Managment");
            System.out.println("\t\t-Found industry");
            System.out.println("\tc. Economy");
            System.out.println("\t\t- Shoe incomes");
            System.out.println("\t\t- Show job detail");
            System.out.println("4. Life");
            System.out.println("\ta. Life Detail");
            System.out.println("\tb. Sleep function");
            System.out.println("\tc. Eat function");
            System.out.println("5. Exit");
            System.out.println("\ta. Are you sure?");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter location or id or title: ");
                    String location = input.next();
                    // process go to location
                    break;
                case 2:
                    System.out.println("a. Show where is character");
                    System.out.println("b. Show options");
                    System.out.print("Enter your choice: ");
                    String option = input.next();
                    // process location based on option
                    break;
                case 3:
                    System.out.println("a. My job");
                    System.out.println("\t- find job");
                    System.out.println("b. Properties");
                    System.out.println("\t- Show properties");
                    System.out.println("\t-Sell");
                    System.out.println("\t-Managment");
                    System.out.println("\t-Found industry");
                    System.out.println("c. Economy");
                    System.out.println("\t- Shoe incomes");
                    System.out.println("\t- Show job detail");
                    System.out.print("Enter your choice: ");
                    String dashboardOption = input.next();
                    // process dashboard based on option
                    break;
                case 4:
                    System.out.println("a. Life Detail");
                    System.out.println("b. Sleep function");
                    System.out.println("c. Eat function");
                    System.out.print("Enter your choice: ");
                    String lifeOption = input.next();
                    // process life based on option
                    break;
                case 5:
                    System.out.print("Are you sure? (y/n): ");
                    String exitOption = input.next();
                    if (exitOption.equalsIgnoreCase("y")) {
                        System.exit(0);
                    }
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (true);
    }
    public static void showMenu(){
       mainMenu();
       String next = scanner.next();
       if (next.equals("1")) {
           game.continueGame(loginMenu());
       }else if(next.equals("2")){
           game.startGame(loginMenu());
       }else if (next.equals("3")){
           joinServer();
       }else if (next.equals("4"))
           System.exit(0);
    }
    public static void mainMenu(){
//        show menu : sout ()
    }

    public static User loginMenu(){
//       get user info : username, password
        return null;
    }

    private static void joinServer(){
        System.out.print("Enter Server Ip Address :");
        String ip = scanner.next();
        System.out.print("Enter Server Port :");
        int port = scanner.nextInt();
        game.joinServer(ip,port);
    }
    public static void main(String[] args) {
            showMenu();
    }
}