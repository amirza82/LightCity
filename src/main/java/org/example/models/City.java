package org.example.models;

import org.example.Database;
import org.example.Information;
import org.example.defualtSystem.Bank;
import org.example.defualtSystem.FastFoodShop;
import org.example.defualtSystem.Municipality;
import org.example.defualtSystem.StockMarket;
import org.example.interfaces.CityInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class City implements CityInterface {
    private final ArrayList<Character> characters;
    private final Bank bankSystem;

    private final StockMarket stockMarket;

    private Municipality municipality = new Municipality();

    public City() {
        characters = new ArrayList<>();
        municipality = new Municipality();
//        Get Bank Property from municipality
        bankSystem = Information.getBank();
        stockMarket = new StockMarket();
        stockMarket.startMarketSimulation();
    }

    @Override
    public void joinCharacter(User userinfo) {
        Character character = new Character(userinfo.getUsername());
        Information.plus(character);
        System.out.println("""

                \033[0;32m **Hello! Welcome to your new city.**"\033[0m
                                
                """);
        beginGame(character);
    }

    @Override
    public void getCityDetail() {
        String players = Arrays.toString(characters.toArray());
    }


    /**
     * Begin Game function generate a new thread for each character ,<b > DO NOT CHANGE THIS FUNCTION STRUCTURE</b> ,
     */
    private void beginGame(Character character) {
        Thread thread = new Thread(() -> {
            try {
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    System.out.println("Menu");
                    do {
                        System.out.println("1. Go to");
                        System.out.println("2. Process Location");
                        System.out.println("\ta. Show where is character");
                        System.out.println("\tb. Show options");
                        System.out.println("3. Dashbord");
                        System.out.println("\ta. My job");
                        System.out.println("\t\t- find job");
                        System.out.println("\tb. Properties");
                        System.out.println("\t\t- Show properties");
                        System.out.println("\t\t-Sell");
                        System.out.println("\t\t-Found industry");
                        System.out.println("\tc. Economy");
                        System.out.println("\t\t- Shoe incomes");
                        System.out.println("\t\t- Show job detail");
                        System.out.println("4. Life");
                        System.out.println("\ta. Life Detail");
                        System.out.println("\tb. Sleep function");
                        System.out.println("\tc. Eat function");
                        System.out.println("5. Exit");
                        System.out.print("Enter your choice: ");
                        int choice = scanner.nextInt();

                        switch (choice) {
                            case 1 -> {
                                System.out.print("Enter location or id or title: ");
                                String option = scanner.next();
                                System.out.println("Enter the " + option);
                                String loc = scanner.next();
                                boolean falseOption = true;
                                while (falseOption) {
                                    switch (option) {
                                        case "location", "id":
                                            character.setInPosition(findProperty(option, loc));
                                            falseOption = false;
                                            break;
                                        case "title":
                                            int id = findIndustry(loc).getId();
                                            for (Property p:Information.properties) {
                                                if (p.getId() == id){
                                                    character.setInPosition(p);
                                                    break;
                                                }
                                            }
                                            falseOption = false;
                                            break;
                                        default:
                                            System.out.println("Wrong Option! write \"location\" or \"title\" or \"id\"");
                                            Scanner optionScann = new Scanner(System.in);
                                            option = optionScann.nextLine();
                                    }
                                }
                            }
                            // process go to location
                            case 2 -> {
                                System.out.println("a. Show where is character");
                                System.out.println("b. Show options");
                                System.out.print("-> Enter your choice: ");
                                String option = scanner.next();
                                if (Objects.equals(option, "a"))
                                    System.out.println(character.getInPosition());
                                else if (option.equals("b")) {
                                    // TODO: 6/16/2023 complete this
                                }
                            }
                            // process location based on option
                            case 3 -> {
                                System.out.println("a. My job");
                                System.out.println("b. Properties");
                                System.out.println("c. Economy");
                                System.out.print("-> Enter your choice: ");
                                String dashboardOption = scanner.next();

                                while (!dashboard(character,dashboardOption)){
                                    System.out.println("Enter valid option : ");
                                    dashboardOption = scanner.nextLine();
                                }
                            }
                            // process dashboard based on option
                            case 4 -> {
                                System.out.println("a. Life Detail");
                                System.out.println("b. Sleep function");
                                System.out.println("c. Eat function");
                                System.out.println("d. Drink function");
                                System.out.print("Enter your choice: ");
                                String lifeOption = scanner.next();
                                while (!lifeMenu(character,lifeOption)){
                                    System.out.println("Enter valid option : ");
                                    lifeOption = scanner.nextLine();
                                }
                            }
                            // process life based on option
                            case 5 -> {
                                System.out.print("Are you sure? (y/n): ");
                                String exitOption = scanner.next();
                                if (exitOption.equalsIgnoreCase("y")) {
                                    Database.write();
                                    System.exit(0);
                                }
                            }
                            default -> System.out.println("Invalid choice!");
                        }


                        character.getLife().startConsuming();
                        if (!isAlive(character)) break;

                    } while (true);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
    }
    public boolean lifeMenu(Character character, String option){
        switch (option){
            case "a":
                System.out.println(character.getLife());
                return true;
            case "b": {
                System.out.println("How much do you want to sleep ?");
                Scanner scanner = new Scanner(System.in);
                character.getLife().sleep(scanner.nextFloat());
                return true;
            }
            case "c": {
                FastFoodShop shop = new FastFoodShop();
                shop.buyFood(character);
                return true;
            }
            case "d": {
                FastFoodShop shop = new FastFoodShop();
                shop.buyWater(character);
                return true;
            }
            default:
                return false;
        }
    }
    public boolean dashboard(Character character, String dashboardOption) {
        switch (dashboardOption) {
            case "a":
                return true;
            case "b": {
                System.out.println("\t-1. Show properties");
                System.out.println("\t-2. Sell");
                System.out.println("\t-3. Found industry");
                System.out.print("-> Enter your choice: ");
                Scanner propertyScann = new Scanner(System.in);
                int properyChoice = propertyScann.nextInt();
                boolean falseChoice = true;
                while (falseChoice) {
                    switch (properyChoice) {
                        case 1:
                            for (Property p : character.getProperties()) {
                                System.out.println(p);
                            }
                            falseChoice = false;
                            break;
                        case 2: {
                            System.out.println("Enter your property id :");
                            int pId = propertyScann.nextInt();
                            Property propertyForSell =null;
                            for (Property p:Information.properties) {
                                if (p.getId()==pId && p.owner == character)
                                    propertyForSell = p;
                            }
                            if (propertyForSell!=null)
                                municipality.sellProperty(propertyForSell,character);
                            else
                                System.out.println("You do not have such property");

                            falseChoice = false;
                            break;
                        }
                        case 3:
                            System.out.println("Enter id of related property : ");
                            int id = propertyScann.nextInt();
                            boolean haveIndustry = false;
                            boolean owned = false;
                            for (Industry i : Information.industry) {
                                if (i.getId() == id) {
                                    haveIndustry = true;
                                    System.out.println("This property already has industry!");
                                    break;
                                }
                            }
                            for (Property p : character.getProperties()) {
                                if (p.getId() == id) {
                                    owned = true;
                                    break;
                                }
                            }
                            if (!owned)
                                System.out.println("You have not this property");
                            else {
                                if (!haveIndustry) {
                                    Industry industry = new Industry("dsvf", 0, 0);
                                    System.out.println("Enter firs amount of industry money : ");
                                    float money = propertyScann.nextFloat();
                                    if (character.getAccount().getMoney() >= money) {
                                        industry.setIncome(money);
                                        character.getAccount().withdraw(money);
                                        industry.setId(id);
                                        System.out.println("Enter title");
                                        Scanner stringScann = new Scanner(System.in);
                                        String title = stringScann.nextLine();
                                        industry.setTitle(title);
                                    } else
                                        System.out.println("You don't have enough money !");
                                }
                            }
                            falseChoice = false;
                            break;
                        default:
                            System.out.println("Invalid choice!");
                    }
                }
                return true;
            }
            case "c": {
                System.out.println("\t\t-1. Shoe incomes");
                System.out.println("\t\t-2. Show job detail");
                Scanner intScan = new Scanner(System.in);
                int economyChoice = intScan.nextInt();
                if (economyChoice == 1){
                    // Shoe incomes
                } else if (economyChoice == 2) {
                    System.out.println(character.getJob());
                }
                else
                    System.out.println("Invalid choice !");
                return true;
            }
            default:
                return false;
        }
    }

    private boolean isAlive(Character root) {
        return !(root.getLife().getFood() <= 0)
                && !(root.getLife().getSleep() <= 0)
                && !(root.getLife().getWater() <= 0);
    }

    public Property findProperty(String option, String loc) {
        if (option.equals("location")) {
            String[] parts = loc.split(" ");
            while (parts.length != 2) {
                System.out.println("Error! wrong input for location. please enter sth like this: 2 7");
                Scanner scanner = new Scanner(System.in);
                String l = scanner.next();
                parts = l.split(" ");
            }
            int x = Integer.parseInt(parts[0]), y = Integer.parseInt(parts[1]);
            for (Property p : municipality.getProperties()) {
                if (p.getCoordinate()[0] == x && p.getCoordinate()[1] == y)
                    return p;
            }
            System.out.println("No property has this location!");
            return null;
        }
        else{
            for (Property p : municipality.getProperties()) {
                if (p.getId() == Integer.parseInt(loc))
                    return p;
            }
            System.out.println("No property with this id!");
            return null;
        }
    }
    public Industry findIndustry(String loc){
        for (Industry i : Information.industry) {
            if (i.getTitle().equals(loc))
                return i;
        }
        System.out.println("No property with this title!");
        return null;
    }

}
