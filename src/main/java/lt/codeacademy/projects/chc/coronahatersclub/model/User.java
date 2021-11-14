package lt.codeacademy.projects.chc.coronahatersclub.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.codeacademy.projects.chc.coronahatersclub.enums.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @ManyToMany(mappedBy = "users")
    private List<Comment> comments = new ArrayList<>();

    private ZonedDateTime dateCreated;

    //personal info
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String country;
    private String address;

    @Column(length = 40)
    private String title;

    @Column(length =250)
    private String aboutMe;

    //path
    private String profileImage;

    private LocalDate birthdate;

    private Boolean locked = false;
    private Boolean enabled = false;

    public User(String username, String password, String email,UserRole role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.dateCreated = LocalDateTime.now().atZone(ZoneId.of("UTC"));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authority);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
