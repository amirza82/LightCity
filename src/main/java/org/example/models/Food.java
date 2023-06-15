package org.example.models;

public class Food {

    private String title;
    private final float water;
    private final float food;
    int id;
    public boolean available;

    public Food(String title, float water, float food, int id, boolean available) {
        this.title = title;
        this.water = water;
        this.food = food;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getWater() {
        return water;
    }

    public float getFood() {
        return food;
    }

    @Override
    public String toString() {
        return "Food{" +
                "title='" + title + '\'' +
                ", water=" + water +
                ", food=" + food +
                ", id=" + id +
                ", available=" + available +
                '}';
    }
}
