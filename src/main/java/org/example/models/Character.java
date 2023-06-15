package org.example.models;

import org.example.Information;
import org.example.defualtSystem.Life;
import org.example.interfaces.CharacterInterface;

import java.util.ArrayList;
import java.util.Date;

public class Character implements CharacterInterface {
    private User userInfo;
    private BankAccount account;
    private Life life;
    private Job job;
    private ArrayList<Property> properties = new ArrayList<>();
    boolean accountFound;
    private Property inPosition;

    public Character(String username, int jobId,
                     ArrayList<Integer> propertiesId, int inPositionId) {
        for (User u: Information.users) {
            if (u.getUsername().equals(username)){
                userInfo = u;
                break;
            }
        }
        accountFound = false;
        for (BankAccount b:Information.bankAccounts) {
            if (b.getOwner().equals(username)){
                account = b;
                accountFound = true;
                break;
            }
        }
        if (!accountFound)
            account = new BankAccount(username,10,new Date());

        for (Property p:Information.properties) {
            if (p.getId() == inPositionId)
                inPosition = p;

            if (propertiesId.contains(p.getId()))
                properties.add(p);
        }

        for (Job j:Information.jobs) {
            if (j.getId() == jobId){
                this.job = j;
                break;
            }
        }

        for (Life l:Information.lives) {
            if (l.getUsername().equals(username)){
                this.life = l;
                break;
            }
        }
    }

    public Character(String username) {
        for (User u: Information.users) {
            if (u.getUsername().equals(username)){
                userInfo = u;
                break;
            }
        }
        account = new BankAccount(username, 10,new Date());

        inPosition = null;

        job = null;

        life = new Life(10,10,10,username);
    }


    public User getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(User userInfo) {
        this.userInfo = userInfo;
    }

    public BankAccount getAccount() {
        return account;
    }

    public void setAccount(BankAccount account) {
        this.account = account;
    }

    public Life getLife() {
        return life;
    }

    public void setLife(Life life) {
        this.life = life;
    }

    public Property getInPosition() {
        return inPosition;
    }

    public void setInPosition(Property inPosition) {
        this.inPosition = inPosition;
    }

    public void gotToLocation(Property destination){
        if(destination==null)return;
        inPosition = destination;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public ArrayList<Property> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
    }

    @Override
    public void positionProcessing() {

    }

    @Override
    public String toString() {
        return "Character{" +
                "userInfo=" + userInfo +
                ", account=" + account +
                ", life=" + life +
                ", job=" + job +
                ", properties=" + properties +
                ", accountFound=" + accountFound +
                ", inPosition=" + inPosition +
                '}';
    }
}
