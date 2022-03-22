package mysecurity.myjwt.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public final class UserResponse {

    private final Long id;
    private final String email;
    private final LocalDateTime createdTime;
    private final String role;

}
