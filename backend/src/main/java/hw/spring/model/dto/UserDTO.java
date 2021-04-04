package hw.spring.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import hw.spring.common.email.ValidEmail;
import hw.spring.common.password.PasswordMatches;
import hw.spring.model.user.Sex;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@PasswordMatches
public class UserDTO {

    @ValidEmail
    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private String matchingPassword;

    private String name;
    private String surname;
    private short age;
    private Sex sex;
    private Date dateOfBirth;

    public UserDTO(@JsonProperty(value = "email") String email,
                   @JsonProperty(value = "username") String username,
                   @JsonProperty(value = "password", required = true) String password,
                   @JsonProperty(value = "matchingPassword", required = true) String matchingPassword,
                   @JsonProperty(value = "name", defaultValue = "Jan") String name,
                   @JsonProperty(value = "surname", defaultValue = "Kowalski") String surname,
                   @JsonProperty(value = "age", defaultValue = "20") short age,
                   @JsonProperty(value = "sex", defaultValue = "FEMALE") Sex sex,
                   @JsonProperty(value = "dateOfBirth", defaultValue = "1989-12-21") Date dateOfBirth) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.matchingPassword = matchingPassword;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public UsernamePasswordAuthenticationToken toEmailAuthenticationToken() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }

    public UsernamePasswordAuthenticationToken toUsernameAuthenticationToken() {
        return new UsernamePasswordAuthenticationToken(username, password);
    }
}
