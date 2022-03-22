package mysecurity.myjwt.entities.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
public class UserRegistration {

    @NotNull(message = "{application.constraints.email.NotNull.message}")
    @Email(message = "{application.constraints.email.Email.message}")
    @Size(min = 2, max = 50, message = "{application.constraints.email.Size.message}")
    private String email;

    @NotNull(message = "{application.constraints.password.NotNull.message}")
    @Size(min = 5,max = 25,message = "{application.constraints.password.Size.message}")
    private String password;

    @NotNull(message = "application.constraints.password-repeat.NotNull.message")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String passwordRepeat;

}

