package hw.spring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class UserDTO {

    private final String email;
    private final String username;
    private final String password;

    public UserDTO(@JsonProperty(value = "email", defaultValue = "default@df.net") String email,
                   @JsonProperty(value = "username", defaultValue = "defaultUser") String username,
                   @JsonProperty(value = "password", required = true) String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public UsernamePasswordAuthenticationToken toAuthenticationToken() {
        //TODO: I don't know if email or username?
        UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(email, password);
        return upat;
    }
}
