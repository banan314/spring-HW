package hw.spring.model.user;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hw.spring.common.serializer.ActivitiesListSerializer;
import hw.spring.common.serializer.CustomDateSerializer;
import hw.spring.model.activity.Activity;
import hw.spring.model.user.role.Role;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

/**
 * Created by Kamil on 31-Mar-17.
 */
@Entity
@Table(
        name = "users",
        uniqueConstraints =
                @UniqueConstraint(columnNames = {"email", "username"})
)
public class User implements Serializable {

    private @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq") int id;

    private String email;
    private String username;
    private String name;
    private String surname;
    private short age;

    private Sex sex;
    private String password;

    @Column(name = "LASTPASSWORDRESETDATE")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private java.util.Date lastPasswordResetDate;

    @JsonSerialize(using = CustomDateSerializer.class)
    private Date dateOfBirth;

    @JsonSerialize(using = ActivitiesListSerializer.class)
    @ManyToMany(mappedBy = "ownerUsers")
    private List<Activity> activities = new ArrayList<>();

    @ManyToMany(mappedBy = "users", cascade = ALL, fetch = EAGER)
    private Set<Role> roles;

    public User() {
    }

    public User(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.age = user.getAge();
        this.sex = user.getSex();
        this.roles = user.getRoles();
        this.password = user.getPassword();
        this.dateOfBirth = user.getDateOfBirth();
        this.activities = user.getActivities();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public Activity addActivity(Activity activity) {
        this.activities.add(activity);
        return activity;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", age=" + age + ", sex=" + sex + ", dateOfBirth=" + dateOfBirth + '}';
    }

    public String getFirstname() {
        return "N/A";
    }

    public String getLastname() {
        return "N/A";
    }

    public Collection<Role> getAuthorities() {
        return roles;
    }

    public Boolean getEnabled() {
        return true;
    }

    public java.util.Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }
}
