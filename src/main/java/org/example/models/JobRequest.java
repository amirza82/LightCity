package org.example.models;

import java.util.ArrayList;

public class JobRequest
{
    private int id;
    private static int idcode=1;
    private int Numberofrequests;
    private String title;
    private float baseSalary;
    private int baceLevel;
    private ArrayList<Character> peoplerequest=new ArrayList<>();

    public JobRequest(int numberofrequests, String title, float baseSalary, int baceLevel) {
        Numberofrequests = numberofrequests;
        this.title = title;
        this.baseSalary = baseSalary;
        this.baceLevel = baceLevel;
        this.id=idcode;
        idcode++;
    }

    public int getNumberofrequests() {
        return Numberofrequests;
    }

    public void setNumberofrequests(int numberofrequests) {
        Numberofrequests = numberofrequests;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Character> getPeoplerequest() {
        return peoplerequest;
    }

    public void setPeoplerequest(ArrayList<Character> peoplerequest) {
        this.peoplerequest = peoplerequest;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(float baseSalary) {
        this.baseSalary = baseSalary;
    }

    public int getBaceLevel() {
        return baceLevel;
    }

    public void setBaceLevel(int baceLevel) {
        this.baceLevel = baceLevel;
    }

    public void printrequest(){
        if (peoplerequest.size()==0)
        {
            System.out.println("No character has applied for this job!!");
        }else {
            for (Character character : peoplerequest) {
                System.out.println(character.getUserInfo().getUsername() + " - " + character.getLevel());
            }
        }
    }
    @Override
    public String toString() {
          return id+" - "+title+" - "+"baceSalary: "+baseSalary+" - "+"baceLevel: "+baceLevel+" - "+"Number_Of_Requests: "+Numberofrequests;
    }
}
