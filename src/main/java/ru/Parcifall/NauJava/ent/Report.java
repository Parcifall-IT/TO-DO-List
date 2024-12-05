package ru.Parcifall.NauJava.ent;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "tbl_reports")
public class Report {
    @Id
    @GeneratedValue
    private long id;
    private ReportStatus status;
    @Column(columnDefinition = "TEXT")
    private String content;

    public Report() {
        status = ReportStatus.CREATED;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", status=" + status +
                ", content='" + content + '\'' +
                '}';
    }
}
