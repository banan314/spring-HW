package hw.spring.services.user;

import hw.spring.common.exceptions.EmailExistsException;
import hw.spring.model.dto.UserRegistrationDTO;
import hw.spring.model.repositories.RoleRepository;
import hw.spring.model.repositories.UserRepository;
import hw.spring.model.user.User;
import hw.spring.model.user.role.Role;
import hw.spring.model.user.role.RoleName;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by Kamil on 31-Mar-17.
 */
@Service
public class UserDefaultService implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserDefaultService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAllByOrderById();
    }

    @Override
    public Optional<User> getById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public void addUser(User user) {
        if (null != userRepository) {
            userRepository.save(user);
        }
    }

    @Override
    public void deleteUser(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
    }

    @Override
    public void updateUser(int id, User user) {
        userRepository.save(user);
    }

    @Transactional
    @Override
    public User registerNewUserAccount(UserRegistrationDTO accountDTO) throws EmailExistsException {
        if (emailExists(accountDTO.getEmail())) {
            throw new EmailExistsException("There is an account with that email address: " + accountDTO.getEmail());
        }

        User user = new User();
        user.setEmail(accountDTO.getEmail());
        user.setUsername(accountDTO.getUsername());
        user.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
        user.setLastPasswordResetDate(Date.valueOf(LocalDate.now()));
        user.setName(accountDTO.getName());
        user.setSurname(accountDTO.getSurname());
        user.setAge(accountDTO.getAge());
        user.setSex(accountDTO.getSex());
        user.setDateOfBirth(accountDTO.getDateOfBirth());

        Set<Role> roles = new HashSet<>(1);
        Role role = roleRepository.findRoleByName(RoleName.ROLE_STUDENT);
        roles.add(role);
        user.setRoles(roles);
        return userRepository.save(user);
    }

    private boolean emailExists(String email) {
        return userRepository.existsUserByEmail(email);
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }
}
