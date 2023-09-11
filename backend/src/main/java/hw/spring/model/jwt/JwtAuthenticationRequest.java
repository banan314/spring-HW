package hw.spring.model.jwt;

import java.io.Serial;
import java.io.Serializable;

public record JwtAuthenticationRequest(String username, String password) implements Serializable {

    @Serial
    private static final long serialVersionUID = -5867678037406733770L;
}
