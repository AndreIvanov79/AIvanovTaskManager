package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Future;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @Column(name = "id",updatable = false,nullable = false, insertable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

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

    public int getId(){return Id;}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setId(int userID){
        this.Id = userID;
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

    @Override
    public String toString() {
        return "User{" +
                "userID=" + Id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", myTasks=" + myTasks +
                '}';
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

