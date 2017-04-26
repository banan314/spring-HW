package hw.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hw.spring.common.serializer.LocalDateSerializer;
import hw.spring.model.user.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by Kamil on 05-Apr-17.
 */
@Entity
public class Activity implements Serializable{
    private @Id @GeneratedValue int id;
    private String name;

    @JsonIgnore
    @ManyToOne
    private User ownerUser;

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

    public User getUser() {
        return this.ownerUser;
    }
    public void setUser(User user) {
        this.ownerUser = user;
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
        this.ownerUser = user;
        return this;
    }
}
