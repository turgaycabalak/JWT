package mysecurity.myjwt.core.security;

import lombok.RequiredArgsConstructor;
import mysecurity.myjwt.dataAccess.UserRepository;
import mysecurity.myjwt.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {

    private final UserPrincipalDao userPrincipalDao;

    @Autowired
    public UserPrincipalDetailsService(@Qualifier("dao-service-v1") UserPrincipalDao userPrincipalDao) {
        this.userPrincipalDao = userPrincipalDao;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userPrincipalDao.getUserPrincipalByUsername(username);
    }

}
