package lt.codeacademy.projects.chc.coronahatersclub.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lt.codeacademy.projects.chc.coronahatersclub.enums.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
public class UserPrincipal implements UserDetails {
    private final Long id;
    private final String email;
    private final String username;
    private final UserRole role;

    @JsonIgnore
    private final String password;

    public UserPrincipal(Long id, String email, String username, String password, UserRole role) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
