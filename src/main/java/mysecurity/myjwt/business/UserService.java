package mysecurity.myjwt.business;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mysecurity.myjwt.dataAccess.UserRepository;
import mysecurity.myjwt.entities.User;
import mysecurity.myjwt.entities.dto.UserDtoConverter;
import mysecurity.myjwt.entities.dto.UserRegistration;
import mysecurity.myjwt.entities.dto.UserResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static mysecurity.myjwt.business.UserRegistrationValidator.*;
import static mysecurity.myjwt.business.UserRegistrationValidator.ValidationResult.*;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;


    public UserResponse saveUser(UserRegistration userRegistration) {

        ValidationResult result = arePasswordsMatching()
                .and(hasEmailBeenUsedBefore())
                .apply(userRegistration, this);
        log.info("User Registration validation completed: " + result.name());

        if(result != SUCCESS){
            log.error("User Registration result is not SUCCESS!");
            throw new BadCredentialsException(result.name());
        }

        User userWillSaveDb = userDtoConverter.convertToUser(userRegistration);
        log.info("Saving new user {} to the DB", userWillSaveDb.getEmail());
        return userDtoConverter.convertToUserResponse(userRepository.save(userWillSaveDb));
    }




    protected boolean checkIfUserExistByEmail(String email){
        return userRepository.existsByEmail(email);
    }


}
