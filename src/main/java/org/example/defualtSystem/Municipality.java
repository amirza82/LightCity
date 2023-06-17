package org.example.defualtSystem;

import org.example.Information;
import org.example.interfaces.MunicipalityInterface;
import org.example.models.Character;
import org.example.models.Property;

import java.util.ArrayList;

public class Municipality implements MunicipalityInterface {
    private ArrayList<Property> properties;

    public  Municipality(){
        this.properties = Information.properties;
    }

//    private void generateProperties() {
////        Create an algorithm for generating properties for city
//    }

    @Override
    public boolean buyProperty(Property property, Character buyer, Character seller, float money) {
        if (Bank.transferMoney(buyer,seller,money)) {
            property.setOwner(buyer);
            return true;
        }
        else {
            System.out.println(buyer.getUserInfo().getUsername() + " can't buy this property");
            return false;
        }

    }

    @Override
    public void sellProperty(Property property, Character seller, float money) {
        if (Bank.transferMoney(Information.getRoot(),seller,money))
            property.setOwner(Information.getRoot());
    }

    @Override
    public void showProperties() {
        for (Property p:properties) {
            System.out.println(p);
        }
    }

    public ArrayList<Property> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
    }
}
