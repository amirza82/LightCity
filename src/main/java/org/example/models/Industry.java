package org.example.models;

import org.example.Information;

import java.util.ArrayList;
import java.util.Arrays;

public class Industry extends Property{
    protected String title;
    protected float income;
    protected ArrayList<Employee> employees = new ArrayList<>();
    protected ArrayList<Job> requiredJobs = new ArrayList<>();


    /**
     * @param title : A Title for generate Industry @example : Bank extends Industry then title = "Bank"
     * @param  income : Each Business has a class like Bank and extends Industry , in super method  Enter the desired monthly income amount
     * */
    public Industry(String title, float income, int propertyId) {
        super(propertyId);
        this.title = title;
        this.income = income;
        if (Information.employees != null)
            for (Employee e: Information.employees) {
                if (e.getIndustry().id == this.id)
                    employees.add(e);
            }
        startPaySalary();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }


    public void employment(Job job, Character character){
        character.setJob(job);
        requiredJobs.remove(job);
        employees.add(new Employee(character.getUserInfo().getUsername(),this.id,job.getIncome(), job.getTitle(),
                job.getLevel()));
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }
    public void addRequiredJob(Job job){

    }

    public ArrayList<Job> getRequiredJobs() {
        return requiredJobs;
    }

    public void setRequiredJobs(ArrayList<Job> requiredJobs) {
        this.requiredJobs = requiredJobs;
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


    public ArrayList<Employee> getEmployee() {
        return employees;
    }

    public void setEmployee(ArrayList<Employee> employee) {
        this.employees = employee;
    }

    public void startPaySalary(){
        Thread thread = new Thread(()->{
            while (true){
                employees.stream().forEach((employee)->{
                    employee.paySalary();
                });
                try {
                    Thread.sleep(10800000); // wait for 1 minute
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    @Override
    public String toString() {
        return "Industry{" +
                "title='" + title + '\'' +
                ", income=" + income +
                ", employees=" + employees +
                ", scales=" + Arrays.toString(scales) +
                ", coordinate=" + Arrays.toString(coordinate) +
                ", owner=" + owner +
                ", id=" + id +
                '}';
    }
}
