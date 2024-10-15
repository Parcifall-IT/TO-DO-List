package ru.Parcifall.NauJava.ent;

import jakarta.persistence.*;

import java.util.Calendar;

@Entity
@Table(name = "tbl_subscriptions")
public class Subscription {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private Calendar period;
    private Double cost;

    public Subscription() {
    }

    public Subscription(String title, Calendar period, Double cost) {
        this.title = title;
        this.period = period;
        this.cost = cost;
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

    public Calendar getPeriod() {
        return period;
    }

    public void setPeriod(Calendar period) {
        this.period = period;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", period=" + period +
                ", cost=" + cost +
                '}';
    }
}
