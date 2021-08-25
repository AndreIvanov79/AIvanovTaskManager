package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tasks")
public class Task implements Serializable {

    @Id
    @Column(name = "id",insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int taskID;

    @Column(name = "task_title")
    private String taskTitle;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)

    private User userID;

    public Task(String taskTitle, String description){
        this.taskTitle=taskTitle;
        this.description=description;
    }

    public Task(){
    }

    public int getTaskID(){return taskID;}

    public String getTaskTitle() {
        return taskTitle;
    }

    public String getDescription() {
        return description;
    }

    public User getUserID(){return userID;}

    public void setTaskID(int taskID){this.taskID = taskID;}

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUserID(User userID){this.userID=userID;}

    @Override
    public String toString() {
        return "Task- "+getTaskTitle()+": "+getDescription();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task= (Task) o;
        return Objects.equals(taskTitle, task.getTaskTitle()) &&
                Objects.equals(description, task.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskTitle,description);
    }
}
