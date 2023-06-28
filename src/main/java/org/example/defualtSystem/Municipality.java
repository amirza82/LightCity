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

    @Override
    public boolean buyProperty(Property property, Character buyer, Character seller) {
        if (Bank.transferMoney(buyer,seller,property.getPropertyPrice())) {
            property.setOwner(buyer);
            return true;
        }
        else {
            System.out.println(buyer.getUserInfo().getUsername() + " can't buy this property");
            return false;
        }

    }

    @Override
    public void sellProperty(Property property, Character seller) {
        if (Bank.transferMoney(Information.getRoot(),seller,property.getPropertyPrice()))
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
