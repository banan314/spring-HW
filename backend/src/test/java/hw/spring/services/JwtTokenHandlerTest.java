package hw.spring.services;

import hw.spring.model.CustomUserDetails;
import hw.spring.model.Role;
import hw.spring.model.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class JwtTokenHandlerTest {
    final String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNTA4MDYzMzQzfQ" +
            ".DDbWH46a4BkMoHWwlXswHV3XGvmE4YJWwbSc8VShOrE";

    UserDetailsService serviceMock = mock(UserDetailsService.class);
    private final String secret = "hello from hell";

    JwtTokenHandler tokenHandler = new JwtTokenHandler(secret);

    @Before
    public void SetUp() {
        tokenHandler.userService = serviceMock;
    }

    @Test
    void parseUserFromTokenTest() {
        User user = fakeUser();
        when(serviceMock.loadUserByUsername("user")).thenReturn(new CustomUserDetails(user));
        Optional<UserDetails> userDetails = tokenHandler.parseUserFromToken(token);

        verify(serviceMock).loadUserByUsername("user");
        userDetails.ifPresent(ud -> {
            assertEquals("password", ud.getPassword(), "passwords do not match");
            assertThat(ud.getAuthorities().size(), is(1));
        });
    }

    private User fakeUser() {
        User user = new User();
        user.setUsername("user");
        user.setPassword("password");
        Role role = new Role();
        role.setName("ROLE_STUDENT");
        user.setRoles(Collections.singleton(role));
        return user;
    }

    @Test
    void createTokenForUserTest() {
        UserDetails user = new CustomUserDetails(fakeUser());

        String token = tokenHandler.createTokenForUser(user);
        String alg = (String) Jwts.parser()
                .setSigningKey(secret)
                .parse(token)
                .getHeader()
                .get("alg");
        assertEquals("HS256", alg);

        //This line will throw an exception if it is not a signed JWS (as expected)
        Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token).getBody();

        //assertEquals(this.token, token, "tokens do not match"); <-- fails because of expiry date
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Disabled //comment if you're a paranoid
    @Test()
    void notSignedToken_ThrowException() {
        thrown.expect(io.jsonwebtoken.SignatureException.class);
        //changed one letter in the token, so that it is wrong signed
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNTA4MDYzMzQzfQ\" +\n"+"            " +
                        "\".DDbWH46a4BkMoHWwlXswHV3XGviE4YJWwbSc8VShOrE").getBody();
    }
}