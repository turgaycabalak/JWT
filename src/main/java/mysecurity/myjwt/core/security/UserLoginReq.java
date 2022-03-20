package mysecurity.myjwt.core.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class UserLoginReq {

    private String username;
    private String password;

}
