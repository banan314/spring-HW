package hw.spring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    private String name;
    private String surname;
    private short age;
    private Sex sex;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private String matchingPassword;

    private Date dateOfBirth;

    public UserDTO(@JsonProperty(value = "email", defaultValue = "default@df.net") String email,
                   @JsonProperty(value = "username", defaultValue = "defaultUser") String username,
                   @JsonProperty(value = "password", required = true) String password,
                   @JsonProperty(value = "matchingPassword", required = true) String matchingPassword,
                   @JsonProperty(value = "name") String name,
                   @JsonProperty(value = "surname") String surname,
                   @JsonProperty(value = "age") short age) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.matchingPassword = matchingPassword;
        this.name = name;
        this.surname = surname;
        this.age = age;
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

    public UsernamePasswordAuthenticationToken toAuthenticationToken() {
        //TODO: I don't know if email or username?
        UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(email, password);
        return upat;
    }
}
