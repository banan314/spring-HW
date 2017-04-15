package model.activity;

import model.user.User;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by Kamil on 05-Apr-17.
 */
public class Activity implements Serializable{
    private long id;
    private String name;

    private long userId;
    private LocalDate startDate;

    public Activity() {
    }

    public Activity(long id, String name, LocalDate startDate) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Activity forUser(User user) {
        this.userId = user.getId();
        return this;
    }
}
