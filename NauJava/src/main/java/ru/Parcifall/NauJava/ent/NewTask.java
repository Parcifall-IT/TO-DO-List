package ru.Parcifall.NauJava.ent;

import jakarta.persistence.*;

import java.util.Calendar;

@Entity
@Table(name = "tbl_tasks")
public class NewTask {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private TaskType type;

    private String title;
    private String description;

    @ManyToOne
    private TaskStatus status;

    private Calendar period;

    public NewTask() {
    }

    public NewTask(TaskType type, String title, String description, TaskStatus status, Calendar period) {
        this.type = type;
        this.title = title;
        this.description = description;
        this.status = status;
        this.period = period;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
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

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Calendar getPeriod() {
        return period;
    }

    public void setPeriod(Calendar period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return "NewTask{" +
                "id=" + id +
                ", type=" + type +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", period=" + period +
                '}';
    }
}
