package lt.codeacademy.projects.chc.coronahatersclub.controller;

import lombok.RequiredArgsConstructor;
import lt.codeacademy.projects.chc.coronahatersclub.model.Comment;
import lt.codeacademy.projects.chc.coronahatersclub.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestRestController {
    private final UserService userService;

    @GetMapping("/user/{userId}/comments")
    public List<Comment> getUserComments(@PathVariable Long userId) {
        return userService.getUserComments(userId);
    }

//    @GetMapping("/comments")
//    public List<User> getComentUsers() {
//
//    }
}

