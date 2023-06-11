package org.example.models;

import org.example.Information;
import org.example.defualtSystem.Bank;

public class Employee {
    private String title;
    private float baseSalary ;
    private int level;
    private Character character;
    private Industry industry;

    public Employee(String characterUsername,String characterpass,int industryId, float baseSalary,
                    String title, int level) {
        this.title = title;

        for (Character c: Information.characters) {
            if ((c.getUserInfo().getUsername().equals(characterUsername))
            && (c.getUserInfo().getPassword().equals(characterpass))){
                this.character = c;
                c.setJob(new Job(title,(baseSalary*level),industryId));
            }
        }
        for (Industry i:Information.industrys) {
            if (i.id == industryId)
                this.industry = i;
        }
        this.baseSalary = baseSalary;
        this.level = level;
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
