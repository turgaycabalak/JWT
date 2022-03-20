package mysecurity.myjwt.core.security;

import lombok.RequiredArgsConstructor;
import mysecurity.myjwt.dataAccess.UserRepository;
import mysecurity.myjwt.entities.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository("dao-service-v1")
public class UserPrincipalDaoService implements UserPrincipalDao{

    private final UserRepository userRepository;

    @Override
    public Optional<UserPrincipal> getUserPrincipalByUsername(String username) {
        User user = userRepository.findByEmail(username).get();

        return Optional.of(new UserPrincipal(
                user.getRole().getGrantedAuthorities(),
                user.getPassword(),
                user.getEmail(),
                true,
                true,
                true,
                user.isEnabled()
        ));
    }

}
