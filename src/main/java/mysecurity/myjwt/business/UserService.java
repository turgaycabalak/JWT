package mysecurity.myjwt.business;

import lombok.RequiredArgsConstructor;
import mysecurity.myjwt.dataAccess.UserRepository;
import mysecurity.myjwt.entities.User;
import mysecurity.myjwt.entities.dto.UserDtoConverter;
import mysecurity.myjwt.entities.dto.UserRegistration;
import mysecurity.myjwt.entities.dto.UserResponse;
import org.springframework.stereotype.Service;

import static mysecurity.myjwt.business.UserRegistrationValidator.*;
import static mysecurity.myjwt.business.UserRegistrationValidator.ValidationResult.*;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;


    public UserResponse saveUser(UserRegistration userRegistration) {
        ValidationResult result = arePasswordsMatching()
                .and(hasEmailBeenUsedBefore())
                .apply(userRegistration, this);
        if(result != SUCCESS){
            throw new IllegalStateException(result.name());
        }

        User userWillSaveDb = userDtoConverter.convertToUser(userRegistration);
        return userDtoConverter.convertToUserResponse(userRepository.save(userWillSaveDb));
    }


    protected boolean checkIfUserExistByEmail(String email){
        return userRepository.existsByEmail(email);
    }


}
