package mysecurity.myjwt.dataAccess;

import lombok.RequiredArgsConstructor;
import mysecurity.myjwt.entities.Role;
import mysecurity.myjwt.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

import static mysecurity.myjwt.entities.Role.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Test
    public void saveUser(){
        User user = new User(
                "turgay",
                passwordEncoder.encode("123456"),
                LocalDateTime.now(),
                SYSTEM_MANAGER
        );

        userRepository.save(user);
    }

    @Test
    public void checkIfEmailExist(){
        String email = "aYAz";
        boolean result = userRepository.existsByEmail(email);
        System.out.println("SONUC : "+result);
    }




}