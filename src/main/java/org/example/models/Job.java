package org.example.models;

public class Job {
    private String title;
    private float income;
    public int industryId;
    private int id;
    private float Level;

    public int getIndustryId() {
        return industryId;
    }

    public void setIndustryId(int industryId) {
        this.industryId = industryId;
    }

    public float getLevel() {
        return Level;
    }

    public void setLevel(int level) {
        Level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param title : Industry title
     * @param income : industry The monthly income of its employees
     * @param industryId : industry id
     * */
    public Job(String title, float income, int industryId, float level) {
        this.title = title;
        this.income = income;
        this.industryId = industryId;
        Level = level;
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

    @Override
    public String toString() {
        return "Job{" +
                "title='" + title + '\'' +
                ", income=" + income +
                ", industryId=" + industryId +
                ", id=" + id +
                '}';
    }
}
