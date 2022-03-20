package mysecurity.myjwt.core.security;


public interface UserPrincipalDao {

    UserPrincipal getUserPrincipalByUsername(String username);

}
