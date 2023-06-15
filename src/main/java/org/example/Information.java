package org.example;

import org.example.defualtSystem.Bank;
import org.example.defualtSystem.Life;
import org.example.models.Character;
import org.example.models.*;

import java.util.ArrayList;

public class Information {
    public static ArrayList<Industry> industrys = new ArrayList<>();
    public static ArrayList<BankAccount> bankAccounts = new ArrayList<>();
    public static ArrayList<Character> characters = new ArrayList<>();
    public static ArrayList<Employee> employees = new ArrayList<>();
    public static ArrayList<Property> properties = new ArrayList<>();
    public static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<Job> jobs = new ArrayList<>();
    public static ArrayList<Life> lives = new ArrayList<>();
    public static Bank bank = new Bank();

    public static Bank getBank(){
        return bank;
    }

    public static int getBiggestId(Object o){
        int id = -10;

        if (o instanceof Industry) {
            for (Industry i:industrys) {
                if (i.getId() > id)
                    id = i.getId();
            }
        }

        if (o instanceof Property) {
            for (Property p:properties) {
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
            industrys.add((Industry) o);
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
        if (o instanceof Life) {
            lives.add((Life) o);
        }
    }

    public static void plus(Object o) {
//        Takes an object and checks what it is. It belongs to any list, adds to that list
        if (o instanceof Industry) {
            ((Industry) o).setId((getBiggestId(o) + 1));
            industrys.add((Industry) o);
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
        if (o instanceof Life) {
            lives.add((Life) o);
        }
    }
}
