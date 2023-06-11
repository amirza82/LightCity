package org.example.models;

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

    public Property(float[] scales, float[] coordinate, Character owner) {
        this.scales = scales;
        this.coordinate = coordinate;
        this.owner = owner;
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
