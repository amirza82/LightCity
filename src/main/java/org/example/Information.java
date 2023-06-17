package org.example;

import org.example.defualtSystem.Bank;
import org.example.defualtSystem.Life;
import org.example.models.Character;
import org.example.models.*;

import java.util.ArrayList;

public class Information {
    public static ArrayList<Industry> industry = new ArrayList<>();
    public static ArrayList<BankAccount> bankAccounts = new ArrayList<>();
    public static ArrayList<Character> characters = new ArrayList<>();
    public static ArrayList<Employee> employees = new ArrayList<>();
    public static ArrayList<Property> properties = new ArrayList<>();
    public static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<Job> jobs = new ArrayList<>();
    public static ArrayList<Life> lives = new ArrayList<>();
    public static ArrayList<Food> foods = new ArrayList<>();
    public static ArrayList<Liquid> liquids = new ArrayList<>();
    public static Bank bank = new Bank();

    public static Bank getBank() {
        return bank;
    }

    public static Character getRoot() {
        for (Character c : characters) {
            if (c.getUserInfo().getUsername().equals("root"))
                return c;
        }
        return null;
    }

    public static int getBiggestId(Object o) {
        int id = -10;

        if (o instanceof Industry) {
            for (Industry i : industry) {
                if (i.getId() > id)
                    id = i.getId();
            }
        }

        if (o instanceof Property) {
            for (Property p : properties) {
                if (p.getId() > id)
                    id = p.getId();
            }
        }

        if (o instanceof Job) {
            for (Job j : jobs) {
                if (j.getId() > id)
                    id = j.getId();
            }
        }
        return id;
    }

    public static void plus(Object o) {
//        Takes an object and checks what it is. It belongs to any list, adds to that list
        if (o instanceof Industry) {
            ((Industry) o).setId((getBiggestId(o) + 1));
            industry.add((Industry) o);
        }
        if (o instanceof BankAccount) {
            bankAccounts.add((BankAccount) o);
        }
        if (o instanceof Character) {
            characters.add((Character) o);
        }
        if (o instanceof Employee) {
            employees.add((Employee) o);
        }
        if (o instanceof Property) {
            ((Property) o).setId(getBiggestId(o) + 1);
            properties.add((Property) o);
        }
        if (o instanceof User) {
            users.add((User) o);
        }
        if (o instanceof Job) {
            ((Job) o).setId(getBiggestId(o) + 1);
            jobs.add((Job) o);
        }
        if (o instanceof Food) {
            ((Food) o).setId(getBiggestId(o) + 1);
            foods.add((Food) o);
        }
        if (o instanceof Liquid) {
            ((Liquid) o).setId(getBiggestId(o) + 1);
            liquids.add((Liquid) o);
        }
        if (o instanceof Life) {
            lives.add((Life) o);
        }
    }

    public static void delete(Object o) {
//        Takes an object and checks what it is. It belongs to any list, adds to that list
        if (o instanceof Industry) {
            industry.remove((Industry) o);
        }
        if (o instanceof BankAccount) {
            bankAccounts.remove((BankAccount) o);
        }
        if (o instanceof Character) {
            characters.remove((Character) o);
        }
        if (o instanceof Employee) {
            employees.remove((Employee) o);
        }
        if (o instanceof Property) {
            ((Property) o).setId(getBiggestId(o) + 1);
            properties.remove((Property) o);
        }
        if (o instanceof User) {
            users.remove((User) o);
        }
        if (o instanceof Job) {
            jobs.remove((Job) o);
        }
        if (o instanceof Life) {
            lives.remove((Life) o);
        }
        if (o instanceof Food) {
            foods.remove((Food) o);
        }
        if (o instanceof Liquid) {
            liquids.remove((Liquid) o);
        }
    }
}
