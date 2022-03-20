package mysecurity.myjwt.business;

import mysecurity.myjwt.entities.dto.UserRegistration;

import java.util.function.BiFunction;

import static mysecurity.myjwt.business.UserRegistrationValidator.*;
import static mysecurity.myjwt.business.UserRegistrationValidator.ValidationResult.*;

// Combinator pattern
public interface UserRegistrationValidator extends BiFunction<UserRegistration, UserService, ValidationResult> {

    static UserRegistrationValidator hasEmailBeenUsedBefore(){
        return (userRegistration, userService) -> !userService.checkIfUserExistByEmail(userRegistration.getEmail()) ?
                SUCCESS : EMAIL_ALREADY_IN_USE;
    }

    static UserRegistrationValidator arePasswordsMatching(){
        return (userRegistration, userService) -> userRegistration.getPassword().equals(userRegistration.getPasswordRepeat()) ?
                SUCCESS : PASSWORDS_NOT_MATCHING;
    }



    default UserRegistrationValidator and (UserRegistrationValidator other){
        return (userRegistration, userService) -> {
            ValidationResult result = this.apply(userRegistration, userService);
            return result.equals(SUCCESS) ? other.apply(userRegistration, userService) : result;
        };
    }

    enum ValidationResult{
        SUCCESS,
        EMAIL_ALREADY_IN_USE,
        PASSWORDS_NOT_MATCHING
    }
}
