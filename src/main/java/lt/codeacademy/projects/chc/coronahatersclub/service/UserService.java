package lt.codeacademy.projects.chc.coronahatersclub.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.codeacademy.projects.chc.coronahatersclub.enums.UserRole;
import lt.codeacademy.projects.chc.coronahatersclub.model.User;
import lt.codeacademy.projects.chc.coronahatersclub.repository.UserRepository;
import lt.codeacademy.projects.chc.coronahatersclub.requests.UserEditRequest;
import lt.codeacademy.projects.chc.coronahatersclub.security.UserPrincipal;
import lt.codeacademy.projects.chc.coronahatersclub.validators.UserActionValidator;
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
    private final UserActionValidator actionValidator;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        return userRepository.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail)
                .map(user -> new UserPrincipal(user.getId(),user.getEmail(),user.getUsername(),user.getPassword(),user.getRole()))
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));

    }

    public String register(User user) {
        actionValidator.validateRegister(user);
        String encodedPassword = bCryptPasswordEncoder
                .encode(user.getPassword());

        user.setPassword(encodedPassword);
        user.setRole(UserRole.USER);

        userRepository.save(user);
        return "registerSuccess";
    }

    public void editUser(UserEditRequest edit, User user) {
        LocalDate newDate;
        try {
            if (edit.getBirthYear() != null && edit.getBirthMonth() != null && edit.getBirthDay() != null) {
                newDate = LocalDate.of(edit.getBirthYear(), edit.getBirthMonth(), edit.getBirthDay());
            } else {
                newDate = user.getBirthdate();
            }
        } catch (Exception e) {
            throw e;
        }
        actionValidator.validateEdit(user,edit,newDate);
        userRepository.save(user);
    }
}
