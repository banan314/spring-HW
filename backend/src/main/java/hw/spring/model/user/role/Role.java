package hw.spring.model.user.role;

import com.fasterxml.jackson.annotation.JsonBackReference;
import hw.spring.model.user.User;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"name"}),
        name = "roles"
)
public class Role implements GrantedAuthority {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private RoleName name;

    @ManyToMany
    @JsonBackReference
    private List<User> users = new ArrayList<>();

    public Role() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String getAuthority() {
        return name.name();
    }
}
