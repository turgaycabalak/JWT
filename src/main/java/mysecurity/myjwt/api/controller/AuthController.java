package mysecurity.myjwt.api.controller;

import lombok.RequiredArgsConstructor;
import mysecurity.myjwt.business.UserService;
import mysecurity.myjwt.entities.dto.UserRegistration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;


    @PostMapping("/v1/signup")
    public ResponseEntity<?> signUpUser(@Valid @RequestBody UserRegistration userRegistration){
//        return new ResponseEntity<>(userService.saveUser(userRegistration), HttpStatus.CREATED);

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/auth/v1/signup").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(userRegistration));
    }

    ///////// Login Method   /////////
    ///////// Logout Method  /////////





}

