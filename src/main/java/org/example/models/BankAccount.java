package org.example.models;

import org.example.Information;
import org.example.defualtSystem.Bank;

import java.util.Date;

public class BankAccount {
    private String owner;
//    private String password;
    private float money;
    private Date lastChange;
    private static Bank bank = Information.getBank();

    private String logs = "";

    public void setMoney(float money) {
        this.money = money;
    }

    public BankAccount(String ownerUserName, float money, Date lastChange) {
        this.owner = ownerUserName;
        this.money = money;
        this.lastChange = lastChange;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

    public float getMoney() {
        return money;
    }


    public Date getLastChange() {
        return lastChange;
    }

    public void setLastChange(Date lastChange) {
        this.lastChange = lastChange;
    }

    public boolean withdraw(float amount){
            if(amount <= money){
//                Bank.turnover.transfer(amount,-1);
                money-= amount;
                return true;
            }
        return false;
    }
    public boolean deposit(float amount){
        if(amount >0){
//            String log = String.format("User : %s deposit %f \n",character.getUserInfo().getUsername(),amount);
//            logs+=log;
//            Bank.turnover.transfer(amount,1);
                money += amount;
                return true;
            }
        return false;
    }
}