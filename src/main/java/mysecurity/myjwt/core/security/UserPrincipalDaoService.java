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
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByEmail(username);
    }

}
