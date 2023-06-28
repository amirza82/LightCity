package org.example.models;

public class Liquid {
    private final float liquid;
    int id;
    public float consumerPrice;

    public boolean available;

    public Liquid(float liquid, int id, boolean available) {
        this.liquid = liquid;
        this.id = id;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public float getLiquid() {
        return liquid;
    }

    @Override
    public String toString() {
        return "Liquid{" +
                "liquid=" + liquid +
                ", consumerPrice=" + consumerPrice +
                ", available=" + available +
                '}';
    }
}
