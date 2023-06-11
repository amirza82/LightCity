package org.example.models;

import org.example.Information;
import org.example.defualtSystem.Bank;

import java.util.Date;

public class BankAccount {
    private String owner;
    private String password;
    private float money;
    private Date lastChange;
    private Bank bank =null;

    private String logs = "";

    public void setMoney(float money) {
        this.money = money;
    }

    public BankAccount(String owner, String password) {
        for (Industry i: Information.industrys) {
            if (i.getTitle()=="Bank")
                bank==i;
        }
        boolean found = false;
        for (BankAccount b:bank.getAccounts()) {
            if (b.getOwner().equals(owner) && b.getPassword().equals(password)){
                this.owner = owner;
                this.password = password;
                money = b.getMoney();
                lastChange = b.getLastChange();
                found=true;
            }
        }
        if (!found){
            System.out.println("There is no account with this specifications");
        }
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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