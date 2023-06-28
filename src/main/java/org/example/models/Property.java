package org.example.models;

import org.example.Information;

import java.util.Arrays;

public class Property {
    protected float[] scales;
    protected float[] coordinate;
    protected float propertyPrice;
    protected Character owner ;
    protected int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Property(float[] scales, float[] coordinate, String ownerUserName, int id, float propertyPrice) {
        this.id = id;
        this.scales = scales;
        this.coordinate = coordinate;
        this.propertyPrice = propertyPrice;
        for (Character c: Information.characters) {
            if (c.getUserInfo().getUsername().equals(ownerUserName)){
                this.owner = c;
                break;
            }
        }
    }
    public Property(int id) {
        for (Property p:Information.properties) {
            if (p.getId()==id){
                this.id = id;
                this.scales = p.getScales();
                this.coordinate = p.getCoordinate();
                this.owner = p.getOwner();
            }
        }
    }

    public float[] getScales() {
        return scales;
    }

    public void setScales(float[] scales) {
        this.scales = scales;
    }

    public float getPropertyPrice() {
        return propertyPrice;
    }

    public void setPropertyPrice(float propertyPrice) {
        this.propertyPrice = propertyPrice;
    }

    public Character getOwner() {
        return owner;
    }

    public void setOwner(Character owner) {
        this.owner = owner;
    }

    public float[] getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(float[] coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public String toString() {
        return "Property{" +
                "scales=" + Arrays.toString(scales) +
                ", coordinate=" + Arrays.toString(coordinate) +
                ", owner=" + owner +
                ", id=" + id +
                '}';
    }

}
