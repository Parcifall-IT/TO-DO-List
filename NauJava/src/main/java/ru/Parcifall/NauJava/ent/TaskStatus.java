package ru.Parcifall.NauJava.ent;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_task_statuses")
public class TaskStatus {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;
    private Boolean is_failed;

    public TaskStatus() {
    }

    public TaskStatus(String title, String description, Boolean is_failed) {
        this.title = title;
        this.description = description;
        this.is_failed = is_failed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIs_failed() {
        return is_failed;
    }

    public void setIs_failed(Boolean is_failed) {
        this.is_failed = is_failed;
    }

    @Override
    public String toString() {
        return "TaskStatus{" +
                "title='" + title + '\'' +
                ", is_failed=" + is_failed +
                '}';
    }
}
