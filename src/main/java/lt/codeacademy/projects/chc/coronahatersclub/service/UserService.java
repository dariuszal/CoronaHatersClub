package lt.codeacademy.projects.chc.coronahatersclub.service;

import lombok.RequiredArgsConstructor;
import lt.codeacademy.projects.chc.coronahatersclub.enums.UserRole;
import lt.codeacademy.projects.chc.coronahatersclub.model.Comment;
import lt.codeacademy.projects.chc.coronahatersclub.model.User;
import lt.codeacademy.projects.chc.coronahatersclub.repository.UserRepository;
import lt.codeacademy.projects.chc.coronahatersclub.requests.UserRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public User findByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow();
    }

    // TEST REST CONTROLLER
    public String signUpUser(UserRequest request) {
        boolean userExists = userRepository.findByEmail(request.getEmail()).isPresent() || userRepository.findByUsername(request.getUsername()).isPresent();
        if (userExists) {
            throw new IllegalStateException("Someone is already registered with this email or username");
        }
        String encodedPassword = bCryptPasswordEncoder
                .encode(request.getPassword());

        User newUser = new User(
                request.getUsername(),
                encodedPassword,
                request.getEmail(),
                UserRole.USER
        );

        userRepository.save(newUser);
        return "Created new user";
    }

    public String register(String username, String email, String password) {
        boolean userExists = userRepository.findByEmail(email).isPresent() || userRepository.findByUsername(username).isPresent();
        if (userExists) {
            throw new IllegalStateException("Someone is already registered with this email or username");
        }
        String encodedPassword = bCryptPasswordEncoder
                .encode(password);

        User newUser = new User(
                username,
                encodedPassword,
                email,
                UserRole.USER
        );
        userRepository.save(newUser);
        return "registerSuccess";
    }

    public List<Comment> getUserComments(Long userId) {
        var user = userRepository.findById(userId).orElseThrow();
        return user.getComments();
    }
}
