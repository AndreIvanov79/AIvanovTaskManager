package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "user_name",unique = true)
    private String userName;

   // private int taskCounter = 0;

    //@OneToMany(mappedBy = "user_name", cascade = CascadeType.ALL, orphanRemoval = true)
    //List<Task> myTasks;
/*
    public User(String firstName, String lastName, String userName,List<Task> myTasks) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.myTasks = new ArrayList<Task>();
    }*/

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

    public int getId(){return id;}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setId(int id){
        this.id=id;
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
/*
    public int getTaskCounter() {
        return taskCounter;
    }

    public List<Task> getMyTasks(){
        if (myTasks==null){
        this.myTasks=new ArrayList<Task>();}
        return myTasks;
    }
    public int setTaskCounter(){
        return taskCounter++;
    }*/

    public static User getUserByUserName(String userName){
        return new User(userName);
    }


    @Override
    public String toString() {
        return "\n\nUser: " + "\nName: " + getFirstName() + "\nSurname: " + getLastName() + "\nUsername: "
                + getUserName() ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user= (User) o;
        return Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(userName, user.userName);
               // Objects.equals(taskCounter, user.taskCounter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName,lastName,userName);
    }
}

