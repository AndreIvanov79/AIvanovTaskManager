package entity;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @Column(name = "id",updatable = false,nullable = false, insertable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int userID;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "user_name",unique = true,nullable = false)
    private String userName;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "userID",cascade = CascadeType.ALL)
    private List<Task> myTasks;

    public User(String firstName, String lastName, String userName,List<Task> myTasks) {
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

    public int getUserID(){return userID;}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserID(int userID){
        this.userID = userID;
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

    public List<Task> getMyTasks(){
        if (myTasks==null){
        this.myTasks=new ArrayList<Task>();}
        return myTasks;
    }

    public void addTaskToList(Task task){
        this.getMyTasks().add(task);
        System.out.println("Task added to: "+this.getUserName());
    }

    public Task getTaskFromList(){
        Task res=null;
        for (Task task: this.getMyTasks()){
            res= task;
        }
        return res;
    }

    public static User getUserByUserName(String userName){
        return new User(userName);
    }


    @Override
    public String toString() {
        return "\n\nUser: " + "\nName: " + getFirstName() + "\nSurname: " + getLastName() + "\nUsername: "
                + getUserName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user= (User) o;
        return Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(userName, user.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName,lastName,userName);
    }
}

