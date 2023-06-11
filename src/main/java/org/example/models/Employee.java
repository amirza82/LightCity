package org.example.models;

import org.example.defualtSystem.Bank;

public class Employee {
    private float baseSalary ;
    private int level;
    private Character character;
    private Industry industry;

    public Employee(Character character,Industry industry, float baseSalary) {
        this.character = character;
        this.baseSalary = baseSalary;
        this.industry = industry;
        this.level = 1;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public float getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(float baseSalary) {
        this.baseSalary = baseSalary;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void paySalary(){
        Bank.transferMoney(industry.getOwner(),character,level*baseSalary);
    }
}
