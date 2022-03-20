package mysecurity.myjwt.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UserResponse {

    private Long id;
    private String email;
    private LocalDateTime createdTime;
    private String role;

}
