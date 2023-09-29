package hw.spring.model.activity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hw.spring.common.deserializers.CustomDateDeSerializer;
import hw.spring.common.serializers.CustomDateSerializer;
import hw.spring.model.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * Created by Kamil on 05-Apr-17.
 */
@Entity(name = "activities")
@Getter
@Setter
@NoArgsConstructor
public class Activity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activity_id_seq")
    private int id;

    @Column
    private String name;

    @Column
    private String location;

    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeSerializer.class)
    private Date startDate;

    @Column
    private BigDecimal price;

    @JsonIgnore
    @ManyToMany
    private List<User> ownerUsers;

    public Activity(String name, Date startDate) {
        this.name = name;
        this.startDate = startDate;
    }

    public Activity forUser(User user) {
        ownerUsers.add(user);
        return this;
    }
}
