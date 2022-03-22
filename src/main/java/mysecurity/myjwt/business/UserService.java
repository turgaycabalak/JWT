package mysecurity.myjwt.business;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mysecurity.myjwt.core.exceptions.customExceptions.UserNotFoundException;
import mysecurity.myjwt.dataAccess.UserRepository;
import mysecurity.myjwt.entities.User;
import mysecurity.myjwt.entities.dto.UserDtoConverter;
import mysecurity.myjwt.entities.dto.UserRegistration;
import mysecurity.myjwt.entities.dto.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import static mysecurity.myjwt.business.UserRegistrationValidator.*;
import static mysecurity.myjwt.business.UserRegistrationValidator.ValidationResult.*;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;


    protected boolean checkIfUserExistByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    protected User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User id does not exist: " + userId));
    }

    @Transactional
    public UserResponse saveUser(UserRegistration userRegistration) {
        ValidationResult result = arePasswordsMatching()
                .and(hasEmailBeenUsedBefore())
                .apply(userRegistration, this);
        log.info("User Registration validation completed: " + result.name());

        if (result != SUCCESS) {
            log.error("User Registration result is not SUCCESS!");
            throw new BadCredentialsException(result.name());
        }

        User userWillSaveDb = userDtoConverter.convertToUser(userRegistration);
        log.info("Saving new user {} to the DB", userWillSaveDb.getEmail());
        return UserDtoConverter.convertToUserResponse(userRepository.save(userWillSaveDb));
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public UserResponse findUserById(Long userId) {
        return UserDtoConverter.convertToUserResponse(getUserById(userId));
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Page<UserResponse> findAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(UserDtoConverter::convertToUserResponse);
    }
    @Deprecated
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Page<User> findAllUsers2(Integer pageSize, Integer page) {
        Page<User> users = userRepository.findAll(PageRequest.of(page, pageSize));
        return users;
    }

    @Transactional
    public void deleteUserById(Long userId) {
        User user = getUserById(userId);
        userRepository.deleteById(user.getId());
    }
}
