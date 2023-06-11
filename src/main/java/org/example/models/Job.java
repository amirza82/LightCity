package org.example.models;

public class Job {
    private String title;
    private float income;
    public int industryId;

    /**
     * @param title : Industry title
     * @param income : industry The monthly income of its employees
     * @param industryId : industry id
     * */
    public Job(String title, float income, int industryId) {
        this.title = title;
        this.income = income;
        this.industryId = industryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getIncome() {
        return income;
    }

    public void setIncome(float income) {
        this.income = income;
    }

}
