package ru.Parcifall.NauJava.ent;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_task_types")
public class TaskType {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;
    private Integer importance;

    public TaskType() {
    }

    public TaskType(String title, String description, Integer importance) {
        this.title = title;
        this.description = description;
        this.importance = importance;
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

    public Integer getImportance() {
        return importance;
    }

    public void setImportance(Integer importance) {
        this.importance = importance;
    }

    @Override
    public String toString() {
        return "Type{" +
                "title='" + title + '\'' +
                ", importance=" + importance +
                '}';
    }
}
