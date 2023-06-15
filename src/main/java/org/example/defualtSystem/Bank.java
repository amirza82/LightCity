package org.example.defualtSystem;

import org.example.interfaces.BankInterface;
import org.example.models.Character;
import org.example.models.*;

import java.util.ArrayList;
import java.util.Date;

public class Bank extends Industry implements BankInterface {

    private static final int MAX_EMPLOYEE_COUNT = 5;
    private static final float BASE_EMP_SALARY = 0.5f;

    public static  BankTurnover turnover;

    public Bank() {
        super("Bank",100.0f,0);
        turnover = new BankTurnover();
    }

//    public BankAccount newAccount(String username){
//        BankAccount bankAccount = new BankAccount(username,0,new Date());
////        bankAccount.setMoney(0);
////        bankAccount.setLastChange(new Date());
////        accounts.add(bankAccount);
//        return bankAccount;
//    }
    public boolean registerAsEmp(Character character){
        if(employees.size() >= MAX_EMPLOYEE_COUNT)return false;
        Employee employee = new Employee(character,this,BASE_EMP_SALARY);
        employees.add(employee);
        return true;
    }

    public String bankDetail(Character character){
        if(character==owner){
            return "Bank{" +
                    "manager=" + owner +
                    ", employees=" + employees +
                    '}';
        }
        return "Only Manager can see Bank detail";
    }
    public static boolean transferMoney(Character buyer, Character seller, float amount){
        if (buyer.getAccount().withdraw(amount))
            if (seller.getAccount().deposit(amount))
                return true;
        return false;
    }
}
