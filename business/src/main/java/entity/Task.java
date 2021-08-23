package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tasks")
public class Task implements Serializable {

    @Id
    @Column(name = "task_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "task_title")
    private String taskTitle;

    @Column(name = "description")
    private String description;

    @Column(name = "user_name")
   // @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "user_name")
    private String userName;

    public Task(String userName, String taskTitle, String description){
        this.userName=userName;
        this.taskTitle=taskTitle;
        this.description=description;
    }

    public Task(String taskTitle, String description){
        this.taskTitle=taskTitle;
        this.description=description;
    }

    public Task(){
    }

    public int getId(){return id;}

    public String getTaskTitle() {
        return taskTitle;
    }

    public String getDescription() {
        return description;
    }

    public String getUserName() {
        return userName;
    }

    public void setId(int id){this.id=id;}

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUserName(String userName){
        this.userName=userName;
    }

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
