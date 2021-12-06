package lt.codeacademy.projects.chc.coronahatersclub.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.codeacademy.projects.chc.coronahatersclub.enums.UserRole;
import lt.codeacademy.projects.chc.coronahatersclub.model.User;
import lt.codeacademy.projects.chc.coronahatersclub.repository.UserRepository;
import lt.codeacademy.projects.chc.coronahatersclub.requests.UserEditRequest;
import lt.codeacademy.projects.chc.coronahatersclub.security.UserPrincipal;
import lt.codeacademy.projects.chc.coronahatersclub.validators.UserEditRequestValidator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserEditRequestValidator editValidator;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        return userRepository.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail)
                .map(user -> new UserPrincipal(user.getId(),user.getEmail(),user.getUsername(),user.getPassword(),user.getRole()))
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));

    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow();
    }

    public String register(User user) {
        boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent() || userRepository.findByUsername(user.getUsername()).isPresent();
        if (userExists) {
            throw new IllegalStateException("Someone is already registered with this email or username");
        }
        if (user.getPassword() == null) {
            throw new IllegalArgumentException("Password missing");
        }
        String encodedPassword = bCryptPasswordEncoder
                .encode(user.getPassword());

        user.setPassword(encodedPassword);
        user.setRole(UserRole.USER);

        userRepository.save(user);
        return "registerSuccess";
    }

    public void editUser(UserEditRequest edit, Authentication authentication) {
        LocalDate newDate;
        User user;
        try {
            user = (User) authentication.getPrincipal();
            if (edit.getBirthYear() != null && edit.getBirthMonth() != null && edit.getBirthDay() != null) {
                newDate = LocalDate.of(edit.getBirthYear(), edit.getBirthMonth(), edit.getBirthDay());
            } else {
                newDate = user.getBirthdate();
            }
        } catch (Exception e) {
            throw e;
        }
        editValidator.validate(user,edit,newDate);
        userRepository.save(user);
    }
}
