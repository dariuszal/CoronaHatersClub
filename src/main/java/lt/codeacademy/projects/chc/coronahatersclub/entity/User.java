package lt.codeacademy.projects.chc.coronahatersclub.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lt.codeacademy.projects.chc.coronahatersclub.enums.UserRole;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Entity
@Setter
@Getter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotEmpty
    private String username;

    @NotNull
    private String password;

    @Column(unique = true)
    @Email
    @NotNull
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private ZonedDateTime dateCreated;

    //personal info
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String country;
    private String address;
    private LocalDate birthdate;


    @Column(length = 40)
    private String profileTitle;

    @Column(length =250)
    private String aboutMe;

    //path
    private String profileImage;


    private Boolean locked = false;
    private Boolean enabled = true;

    public User(String username, String password, String email,UserRole role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.dateCreated = LocalDateTime.now().atZone(ZoneId.of("UTC"));
    }

    public User() {
        this.dateCreated = LocalDateTime.now().atZone(ZoneId.of("UTC"));
    }

}
