package hw.spring.model.dto;

import hw.spring.common.email.ValidEmail;
import hw.spring.model.activity.Activity;
import hw.spring.model.user.Sex;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;

public class UserDTO {

    @Getter
    @Setter
    List<Activity> activities;

    @Getter
    @Setter
    @NotNull
    private int id;

    @NotNull
    @NotEmpty
    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private short age;

    @Getter
    @Setter
    private Sex sex;

    @Getter
    @Setter
    private Date dateOfBirth;

    @ValidEmail
    @NotNull
    @NotEmpty
    @Getter
    @Setter
    private String email;
}
