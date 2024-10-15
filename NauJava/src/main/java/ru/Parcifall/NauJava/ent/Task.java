package ru.Parcifall.NauJava.ent;

import java.util.Calendar;

/*
Управление задачами (To-Do List). Можно заводить задачи, менять статусы,
устанавливать сроки выполнения, получать оповещения.
 */
public class Task {
    private Long id;

    private String name;

    private String description;

    private Calendar deadline;

    private Status status;

    public Task(Long id, String name, String description, Calendar deadline, Status status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Calendar getDeadline() {
        return deadline;
    }

    public void setDeadline(Calendar deadline) {
        this.deadline = deadline;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline.getTime() +
                ", status=" + status +
                '}';
    }
}
