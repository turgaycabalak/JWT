package mysecurity.myjwt.api.controller;

import lombok.RequiredArgsConstructor;
import mysecurity.myjwt.business.UserService;
import mysecurity.myjwt.entities.dto.UserRegistration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;


    @PostMapping("/signup")
    public ResponseEntity<?> signUpUser(@RequestBody UserRegistration userRegistration){
        return new ResponseEntity<>(userService.saveUser(userRegistration), HttpStatus.CREATED);
    }

    ///////// Login Method   /////////
    ///////// Logout Method  /////////





}
