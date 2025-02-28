package org.example.models;

import org.example.Information;

import java.util.ArrayList;

public class Industry extends Property{
    protected String title;
    protected float income;
    protected ArrayList<Employee> employees = new ArrayList<>();


    /**
     * @param title : A Title for generate Industry @example : Bank extends Industry then title = "Bank"
     * @param property : Industry owner should have a Property to create this industry on it
     * @param  character : Industry owner
     * @param  income : Each Business has a class like Bank and extends Industry , in super method  Enter the desired monthly income amount
     * */
    public Industry(String title,float income, int propertyId) {
        super(propertyId);
        this.title = title;
        this.income = income;
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
}
