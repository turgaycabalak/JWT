package mysecurity.myjwt.core.security;


import java.util.Optional;

public interface UserPrincipalDao {

    Optional<UserPrincipal> getUserPrincipalByUsername(String username);

}
