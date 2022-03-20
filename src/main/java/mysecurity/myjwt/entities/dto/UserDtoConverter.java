package mysecurity.myjwt.entities.dto;

import lombok.RequiredArgsConstructor;
import mysecurity.myjwt.entities.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static mysecurity.myjwt.entities.Role.*;

@Component
@RequiredArgsConstructor
public class UserDtoConverter {

    private final PasswordEncoder passwordEncoder;


    public User convertToUser(UserRegistration userRegistration){
        return new User(
                userRegistration.getEmail(),
                passwordEncoder.encode(userRegistration.getPassword()),
                LocalDateTime.now(),
                USER
        );
    }

    public UserResponse convertToUserResponse(User user){
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getCreatedTime(),
                user.getRole().name()
        );
    }



}
