package net.yorksolutions.memobe.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    Account owner;

    private String title;
    private String body;
    private Date createdDate;
    private Boolean finished;

    public Memo() {
    }

    public Memo(String title, String body, Account owner) {
        this.owner = owner;
        this.title = title;
        this.body = body;
        this.createdDate = new Date();
        this.finished = false;
    }

    public Long getId() {
        return id;
    }

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }
}
