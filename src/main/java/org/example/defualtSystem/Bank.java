package org.example.defualtSystem;

import org.example.interfaces.BankInterface;
import org.example.models.Character;
import org.example.models.Industry;

public class Bank extends Industry implements BankInterface {
    private static final int MAX_EMPLOYEE_COUNT = 5;
    private static final float BASE_EMP_SALARY = 0.5f;
    public static BankTurnover turnover;

    public Bank() {
        super("Bank", 100.0f, 0);
        turnover = new BankTurnover();
    }

    public String bankDetail(Character character) {
        if (character == owner) {
            return "Bank{" +
                    "manager=" + owner +
                    ", employees=" + employees +
                    '}';
        }
        return "Only Manager can see Bank detail";
    }

    public static boolean transferMoney(Character buyer, Character seller, float amount) {
        if (buyer.getAccount().withdraw(amount))
            if (seller.getAccount().deposit(amount))
                return true;
        return false;
    }
}
