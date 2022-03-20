package mysecurity.myjwt.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegistration {

    private String email;
    private String password;
    private String passwordRepeat;

}

