package entitiy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class User implements Serializable, Entity{
    private String firstName;
    private String lastName;
    private String userName;
    private int taskCounter = 0;
    ArrayList<Task> myTasks;

    public User(String firstName, String lastName, String userName,ArrayList<Task> myTasks) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.myTasks = new ArrayList<Task>();
    }

    public User(String firstName, String lastName, String userName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
    }

    public User(String userName){
        this.userName=userName;
    }

    public User() {
    }

    @Override
    public String getFirstParam() {
        return firstName;
    }

    @Override
    public String getSecondParam() {
        return lastName;
    }

    @Override
    public String getThirdParam() {
        return userName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getTaskCounter() {
        return taskCounter;
    }

    public ArrayList<Task> getMyTasks(){
        if (myTasks==null){
        this.myTasks=new ArrayList<Task>();}
        return myTasks;
    }
    public int setTaskCounter(){
        return taskCounter++;
    }

    public static User getUserByUserName(String userName){
        return new User(userName);
    }


    @Override
    public String toString() {
        return "\n\nUser: " + "\nName: " + getFirstParam() + "\nSurname: " + getSecondParam() + "\nUsername: "
                + getThirdParam() + "\nAmount of Tasks: " + getTaskCounter() ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user= (User) o;
        return Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(taskCounter, user.taskCounter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName,lastName,userName,taskCounter);
    }
}

