package mysecurity.myjwt.core.security;


import mysecurity.myjwt.entities.User;

import java.util.Optional;

public interface UserPrincipalDao {

    Optional<User> getUserByUsername(String username);

}
