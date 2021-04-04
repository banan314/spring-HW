package hw.spring.model.activity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hw.spring.common.serializer.CustomDateSerializer;
import hw.spring.model.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * Created by Kamil on 05-Apr-17.
 */
@Entity(name = "activities")
public class Activity implements Serializable {

     @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activity_id_seq")
     private int id;

    private String name;
    private String location;

    @JsonSerialize(using = CustomDateSerializer.class)
    private Date startDate;

    @JsonIgnore
    @ManyToMany
    private List<User> ownerUsers;

    public Activity() { }

    public Activity(String name, Date startDate) {
        this.name = name;
        this.startDate = startDate;
    }

    public int getId() {
        return id;
    }

    public void setId(long id) {
        this.id = (int) id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public List<User> getOwnerUsers() {
        return this.ownerUsers;
    }

    public void setOwnerUsers(List<User> users) {
        this.ownerUsers = users;
    }

    public Activity forUser(User user) {
        this.ownerUsers.add(user);
        return this;
    }
}
