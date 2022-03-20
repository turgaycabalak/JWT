package mysecurity.myjwt.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
@NoArgsConstructor
public class User extends BaseEntity {

    @Column(name = "email", unique = true, nullable = false, length = 100)
    private String email;

    @Column(nullable = false,length = 100)
    private String password;

    @Column(name = "create_time",nullable = false)
    private LocalDateTime createdTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    private boolean isEnabled = true;


    public User(String email,
                String password,
                LocalDateTime createdTime,
                Role role) {

        this.email = email;
        this.password = password;
        this.createdTime = createdTime;
        this.role = role;
    }
}
