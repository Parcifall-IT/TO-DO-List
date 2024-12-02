package ru.Parcifall.NauJava.ent;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Calendar;

@Entity
@Table(name = "tbl_tasks")
public class Task {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private TaskType type;

    private String title;
    private String description;

    @ManyToOne
    private TaskStatus status;

    private LocalDate deadline;

    public Task() {
    }

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
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

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", type=" + type.toString() +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status.toString() +
                ", period=" + deadline.toString() +
                '}';
    }
}
