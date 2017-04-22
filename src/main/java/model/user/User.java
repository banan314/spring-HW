package model.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import model.activity.Activity;

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
public class User implements Serializable {
   private @Id @GeneratedValue int id;
    private String username;
    private short age;
    private  Sex sex;
    private Date dateOfBirth;

    @JsonBackReference
    @OneToMany(mappedBy = "user")
    private List<Activity> activities = new ArrayList<Activity>();

   public User() {}

   public User(String username, short age, Sex sex, Date dateOfBirth) {
      this.username = username;
      this.age = age;
      this.sex = sex;
      this.dateOfBirth = dateOfBirth;
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
      return "User{" +
              "id=" + id +
              ", username=" + username +
              ", age=" + age +
              ", sex=" + sex +
              ", dateOfBirth=" + dateOfBirth +
              '}';
   }

    public List<Activity> getActivities() {
        return activities;
    }
    public void setActivities(List<Activity> activities) {
       this.activities = activities;
    }
}
