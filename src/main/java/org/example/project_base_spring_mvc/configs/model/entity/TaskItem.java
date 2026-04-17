package org.example.project_base_spring_mvc.configs.model.entity;

import java.time.LocalDate;

public class TaskItem {
    private String id;

    private String title;

    private LocalDate deadline;

    private String priority;
    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TaskItem() {
    }

    public TaskItem(LocalDate deadline, String id, String priority, String title) {
        this.deadline = deadline;
        this.id = id;
        this.priority = priority;
        this.title = title;
    }


}
