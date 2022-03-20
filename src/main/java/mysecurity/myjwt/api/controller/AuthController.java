package mysecurity.myjwt.api.controller;

import lombok.RequiredArgsConstructor;
import mysecurity.myjwt.business.UserService;
import mysecurity.myjwt.entities.dto.UserRegistration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;


    @PostMapping("/signup")
    public ResponseEntity<?> signUpUser(@Valid @RequestBody UserRegistration userRegistration){
        return new ResponseEntity<>(userService.saveUser(userRegistration), HttpStatus.CREATED);
    }

    ///////// Login Method   /////////
//    @PostMapping("/login") // localhost:8080/api/authentication/login
//    public ResponseEntity<?> signIn(@Valid @RequestBody UserLoginReq userLoginReq){
//        return new ResponseEntity<>(authenticationService.signInAndReturnJWT(user),HttpStatus.OK);
//    }

    ///////// Logout Method  /////////





}
