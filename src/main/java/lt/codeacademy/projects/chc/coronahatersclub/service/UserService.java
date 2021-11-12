package lt.codeacademy.projects.chc.coronahatersclub.service;

import lombok.RequiredArgsConstructor;
import lt.codeacademy.projects.chc.coronahatersclub.model.User;
import lt.codeacademy.projects.chc.coronahatersclub.repository.UserRepository;
import lt.codeacademy.projects.chc.coronahatersclub.requests.UserRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElse(findByUsername(email));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }
    public User findByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow();
    }

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
                request.getRole()
        );

        userRepository.save(newUser);
        return "Created new user";
    }


}
