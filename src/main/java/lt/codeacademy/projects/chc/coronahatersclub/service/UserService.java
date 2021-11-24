package lt.codeacademy.projects.chc.coronahatersclub.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.codeacademy.projects.chc.coronahatersclub.enums.UserRole;
import lt.codeacademy.projects.chc.coronahatersclub.model.User;
import lt.codeacademy.projects.chc.coronahatersclub.repository.UserRepository;
import lt.codeacademy.projects.chc.coronahatersclub.requests.UserEditRequest;
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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow();
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow();
    }

//    // TEST REST CONTROLLER
//    public String signUpUser(UserRequest request) {
//        boolean userExists = userRepository.findByEmail(request.getEmail()).isPresent() || userRepository.findByUsername(request.getUsername()).isPresent();
//        if (userExists) {
//            throw new IllegalStateException("Someone is already registered with this email or username");
//        }
//        String encodedPassword = bCryptPasswordEncoder
//                .encode(request.getPassword());
//
//        User newUser = new User(
//                request.getUsername(),
//                encodedPassword,
//                request.getEmail(),
//                UserRole.USER
//        );
//
//        userRepository.save(newUser);
//        return "Created new user";
//    }

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
        log.info("Logging:",edit);
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
        if(edit.getFirstName() !=null && !edit.getFirstName().equals("")) {
            user.setFirstName(edit.getFirstName());
        }
        if (edit.getLastName() != null && !edit.getLastName().equals("")) {
            user.setLastName(edit.getLastName());

        }
        if (edit.getAddress() != null && !edit.getAddress().equals("")) {
            user.setAddress(edit.getAddress());
        }
        if (newDate != null && newDate.isBefore(LocalDate.now())) {
            user.setBirthdate(newDate);
        }
        if (edit.getPhone() != null && edit.getPhone() != "") {
            user.setPhoneNumber(edit.getPhone());
        }
        if (edit.getCountry() != null && !edit.getCountry().equals("")) {
            user.setCountry(edit.getCountry());
        }
        if (edit.getTitle() != null && !edit.getTitle().equals("")) {
            user.setTitle(edit.getTitle());
        }
        if (edit.getAbout() != null && !edit.getAbout().isEmpty()) {
            user.setAboutMe(edit.getAbout());
        }
        userRepository.save(user);
    }
}
