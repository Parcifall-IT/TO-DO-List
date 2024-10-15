package ru.Parcifall.NauJava.ent;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_users")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToOne
    private Subscription subscription;

    @OneToOne
    private NewTask task;

    public User() {
    }

    public User(String name, Subscription subscription) {
        this.name = name;
        this.subscription = subscription;
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

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public NewTask getTasks() {
        return task;
    }

    public void setTasks(NewTask task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subscription=" + subscription +
                ", task=" + task +
                '}';
    }
}
