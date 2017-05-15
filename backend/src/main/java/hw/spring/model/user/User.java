package hw.spring.model.user;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hw.spring.common.serializer.ActivitiesListSerializer;
import hw.spring.common.serializer.CustomDateSerializer;
import hw.spring.model.Activity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Kamil on 31-Mar-17.
 */
@Entity
@Table(name = "users")
public class User implements Serializable {
    public enum Role {ADMIN, STUDENT}

    private @Id @GeneratedValue int id;
    private String email;
    private String username;
    private short age;
    private Sex sex;

    private String firstName, lastName;
    private Role role;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public String getName() {
        return new StringBuffer().append(this.firstName).append(this.lastName)
                .toString();
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    private Date dateOfBirth;

    @JsonSerialize(using = ActivitiesListSerializer.class)
    @ManyToMany(mappedBy = "ownerUsers") private List<Activity> activities = new ArrayList<Activity>();

    public User() {
    }

    public User(String username, String email, short age, Sex sex, Date dateOfBirth, String name) {
        this.username = username;
        this.age = age;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        setEmail(email);

        //TODO: does not work with multiple names (more than 2 words)
        String splitted[] = name.split(" ");
        setName(splitted[0], splitted[1]);
    }

    @NotNull
    public User addActivity(Activity activity) {
        this.activities.add(activity);
        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", age=" + age + ", sex=" + sex + ", dateOfBirth=" + dateOfBirth + '}';
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }
}