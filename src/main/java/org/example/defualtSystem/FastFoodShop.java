package org.example.defualtSystem;

import org.example.models.Industry;

public class FastFoodShop extends Industry {

    /**
     * Industry type example (Business)
     * */
    private static final float INCOME = 0.3f;
    private static final float EMPLOYEE_INCOME = 0.02f;

    /**
     * @param title      : A Title for generate Industry @example : Bank extends Industry then title = "Bank"
     * @param income     : Each Business has a class like Bank and extends Industry , in super method  Enter the desired monthly income amount
     * @param propertyId
     */
    public FastFoodShop(String title, float income, int propertyId) {
        super(title, income, propertyId);
    }
//    public FastFoodShop(String title, Property property, Character character) {
//        super(title, property, character,EMPLOYEE_INCOME);
//    }
}
