package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    public User(String username, String firstName, String lastName, UserStatus userStatus,
                String email, boolean banned, int avatarNumber, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userStatus = userStatus;
        this.email = email;
        this.banned = banned;
        this.avatarNumber = avatarNumber;
        this.password = password;
    }

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "username")
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private UserStatus userStatus;

    @Column(name = "email")
    private String email;

    @Column(name = "banned")
    private boolean banned;

    @Column(name = "avatar_number")
    private int avatarNumber;

    @Column(name = "password")
    private String password;
}