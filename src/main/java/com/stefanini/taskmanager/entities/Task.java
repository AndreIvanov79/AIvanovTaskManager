package com.stefanini.taskmanager.entities;

import java.io.Serializable;
import java.util.Objects;

public class Task implements Serializable {
    private String taskTitle;
    private String description;

    public String getTaskTitle() {
        return taskTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Task(String taskTitle, String description){
        this.taskTitle=taskTitle;
        this.description=description;
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
