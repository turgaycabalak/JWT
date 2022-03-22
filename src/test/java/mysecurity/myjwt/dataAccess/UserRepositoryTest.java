package mysecurity.myjwt.dataAccess;

import lombok.RequiredArgsConstructor;
import mysecurity.myjwt.entities.Role;
import mysecurity.myjwt.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

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
                LocalDateTime.now(),
                "turgay",
                passwordEncoder.encode("123456"),
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

    @Test
    public void findUserByEmail(){
        Optional<User> user = userRepository.findByEmail("ayaz5@mail.com");
        user.orElseThrow(()-> new IllegalStateException("user bulunamadi !!!!!"));
        System.out.println(user);
    }


    @Test
    public void deleteUserById(){
        userRepository.deleteById(7L);
    }




}