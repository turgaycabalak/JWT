package mysecurity.myjwt.api.controller;

import lombok.RequiredArgsConstructor;
import mysecurity.myjwt.business.UserService;
import mysecurity.myjwt.core.genericResponse.SuccessDataResult;
import mysecurity.myjwt.core.genericResponse.SuccessResult;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;
    private final String NOT_VALID_ID = "{application.constraints.id.Min.message}";


    //http://localhost:8080/users/v1/getuserbyid?userId=1
    @GetMapping("/v1/getuserbyid")
    public ResponseEntity<?> findUserById(@RequestParam
                                          @Min(value = 1, message = NOT_VALID_ID) Long userId) {
        return ResponseEntity.ok(new SuccessDataResult<>(userService.findUserById(userId), "User found"));
    }

    //http://localhost:8080/users/v1/getallusers?size=5&page=0&sort=email
    @GetMapping("/v1/getallusers")
    public ResponseEntity<?> findAllUsers(Pageable pageable) {
        return ResponseEntity.ok(userService.findAllUsers(pageable));
    }

    /////////////   Patch Mapping  update   ////////////////


    //http://localhost:8080/users/v1/deleteuser/5
    @DeleteMapping("/v1/deleteuser/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id")
                                            @Min(value = 1, message = NOT_VALID_ID) Long userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.ok(new SuccessResult("Data successfully removed. Deleted Id: " + userId));
    }

}
