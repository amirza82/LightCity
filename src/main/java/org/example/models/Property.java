package org.example.models;

import org.example.Information;

public class Property {
    protected float[] scales;
    protected float[] coordinate;
    protected Character owner ;
    protected int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Property(float[] scales, float[] coordinate, String ownerUserName, int id) {
        this.id = id;
        this.scales = scales;
        this.coordinate = coordinate;
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
}
