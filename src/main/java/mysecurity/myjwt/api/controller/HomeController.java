package mysecurity.myjwt.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class HomeController {



    @GetMapping("/")
    public ResponseEntity<?> getHello(){
        return ResponseEntity.ok("Welcome to home page!!!");
    }

    @GetMapping("/users")
//    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'SYSTEM_MANAGER')")
    public ResponseEntity<?> getUser(){
        return ResponseEntity.ok("User Page...");
    }

    @GetMapping("/admins")
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'SYSTEM_MANAGER')")
    public ResponseEntity<?> getAdmin(){
        return ResponseEntity.ok("ADMINISTRATION Page...");
    }

//    @GetMapping("/systems")
////    @PreAuthorize("hasAnyAuthority('user:read','user:write','admin:read','admin:write')")
//    public ResponseEntity<?> getSystemManager(){
//        return ResponseEntity.ok("SYSTEM-MANAGER Page...");
//    }

    @PostMapping("/systems")
    public ResponseEntity<?> getSystemManager(){
        return ResponseEntity.ok("SYSTEM-MANAGER Page...");
    }

}
