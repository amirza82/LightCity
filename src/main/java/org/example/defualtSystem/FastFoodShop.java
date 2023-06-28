package org.example.defualtSystem;

import org.example.Information;
import org.example.models.Character;
import org.example.models.Food;
import org.example.models.Industry;
import org.example.models.Liquid;

import java.util.ArrayList;
import java.util.Scanner;

public class FastFoodShop extends Industry {
    private static final float EMPLOYEE_INCOME = 0.02f;
    public ArrayList<Food> foods = new ArrayList<>();
    public ArrayList<Liquid> water = new ArrayList<>();

    public float getEmployeeIncome() {
        return EMPLOYEE_INCOME;
    }

    public FastFoodShop() {
        super(Information.getShop().getTitle(), Information.getShop().getIncome(), Information.getShop().getId());
        foods = Information.foods;
        water = Information.liquids;
    }

    public void buyFood(Character character) {
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < foods.size(); i++) {
            System.out.println(i+1 + " : ");
            System.out.println(foods.get(i));
        }
        while (true) {
            System.out.println("Your choice : (0 to back)");
            int answer = input.nextInt();

                if (answer == 0) {
                    return;
                } else if (answer <= foods.size()+1 && answer>0) {
                    if (Bank.transferMoney(character,this.owner,foods.get(answer-1).consumerPrice)) {
                        character.getLife().foodConsumption(foods.get(answer - 1));
//                      The shopkeeper buys the sold goods again from the municipality at 90% of the selling price
                        Bank.transferMoney(this.owner,Information.getRoot(), (float) (0.9*(foods.get(answer-1).consumerPrice)));
                    } else {
                        System.out.println("Not Enough Money");
                    }
                    break;
                } else {
                    System.out.println("Enter correct value");
                }
        }

    }

    public void buyWater(Character character) {
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < water.size(); i++) {
            System.out.println(i+1 + " : ");
            System.out.println(water.get(i));
        }
        while (true) {
            System.out.println("Your choice : (0 to back)");
            int answer = input.nextInt();

            if (answer == 0) {
                return;
            } else if (answer <= water.size()+1 && answer>0) {
                if (Bank.transferMoney(character,this.owner,water.get(answer-1).consumerPrice)) {
                    character.getLife().liquidConsumption(water.get(answer - 1));
//                      The shopkeeper buys the sold goods again from the municipality at 90% of the selling price
                    Bank.transferMoney(this.owner,Information.getRoot(), (float) (0.9*(water.get(answer-1).consumerPrice)));
                } else {
                    System.out.println("Not Enough Money");
                }
                break;
            } else {
                System.out.println("Enter correct value");
            }
        }

    }

}

