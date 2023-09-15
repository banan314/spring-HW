package hw.spring.model.dto;

import hw.spring.model.user.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserDTOConverter {
    public UserDTO fromUser(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setAge(user.getAge());
        userDTO.setSex(user.getSex());
        userDTO.setDateOfBirth(user.getDateOfBirth());
        userDTO.setActivities(user.getActivities());
        userDTO.setEmail(user.getEmail());

        return userDTO;
    }
}
