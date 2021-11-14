package lt.codeacademy.projects.chc.coronahatersclub.controller;

import lombok.RequiredArgsConstructor;
import lt.codeacademy.projects.chc.coronahatersclub.model.User;
import lt.codeacademy.projects.chc.coronahatersclub.requests.UserRequest;
import lt.codeacademy.projects.chc.coronahatersclub.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserRestController {

    private final UserService userService;

    @PostMapping
    public String createNewUser(@RequestBody UserRequest request) {
        return userService.signUpUser(request);
    }
    @GetMapping("/email")
    public User findUserByEmail(@RequestParam String email){
        return userService.findByEmail(email);
    }
    @GetMapping("/username")
    public User findUserByUsername(@RequestParam String username){
        return userService.findByUsername(username);
    }
}
