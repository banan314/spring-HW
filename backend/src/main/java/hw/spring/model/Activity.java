package hw.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hw.spring.common.serializer.LocalDateSerializer;
import hw.spring.model.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Kamil on 05-Apr-17.
 */
@Entity(name = "activities")
public class Activity implements Serializable{
    private @Id @GeneratedValue int id;
    private String name;
    private String location;

    @JsonIgnore
    @ManyToMany
    private List<UserActivity> ownerUsers;

    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate startDate;

    public Activity() { }

    public Activity(String name, LocalDate startDate) {
        this.name = name;
        this.startDate = startDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = (int) id;
    }

    public List<UserActivity> getUser() {
        return this.ownerUsers;
    }

    public void setUser(List<UserActivity> users) {
        this.ownerUsers = users;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Activity forUser(UserActivity user) {
        this.ownerUsers.add(user);
        return this;
    }
}
