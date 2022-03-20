package mysecurity.myjwt.core.security;

import lombok.RequiredArgsConstructor;
import mysecurity.myjwt.dataAccess.UserRepository;
import mysecurity.myjwt.entities.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;


@RequiredArgsConstructor
@Repository("dao-service-v1")
public class UserPrincipalDaoService implements UserPrincipalDao{

    private final UserRepository userRepository;

    @Override
    public UserPrincipal getUserPrincipalByUsername(String username) throws UsernameNotFoundException{
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));

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
