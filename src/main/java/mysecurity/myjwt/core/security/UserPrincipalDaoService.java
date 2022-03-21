package mysecurity.myjwt.core.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mysecurity.myjwt.dataAccess.UserRepository;
import mysecurity.myjwt.entities.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Slf4j
@RequiredArgsConstructor
@Repository("dao-service-v1")
public class UserPrincipalDaoService implements UserPrincipalDao{

    private final UserRepository userRepository;

    @Override
    public UserPrincipal getUserPrincipalByUsername(String username) throws UsernameNotFoundException{
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> {
                    log.error("Username not found: " + username);
                    throw new UsernameNotFoundException("Username not found: " + username);
                });

        log.info("User found in the DB: {}", username);
        return new UserPrincipal(
                user.getRole().getGrantedAuthorities(),
                user.getPassword(),
                user.getEmail(),
                true,
                true,
                true,
                user.isEnabled()
        );
    }

}
