package lt.codeacademy.projects.chc.coronahatersclub.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.codeacademy.projects.chc.coronahatersclub.entity.User;
import lt.codeacademy.projects.chc.coronahatersclub.dto.UserEditRequest;
import lt.codeacademy.projects.chc.coronahatersclub.service.CommentService;
import lt.codeacademy.projects.chc.coronahatersclub.service.PostService;
import lt.codeacademy.projects.chc.coronahatersclub.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping
@Controller
public class UserController {

    private final UserService userService;
    private final PostService postService;
    private final CommentService commentService;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String createNewUser(@ModelAttribute User user) {
        userService.register(user);
        return "registersuccess";
    }

    //    @PreAuthorize("authentication.principal.userid == #userId")
    @GetMapping(path = "/user/profile")
    public String userProfile(@ModelAttribute(name = "loggedUser") User user, Model model) {
        model.addAttribute("edit", new UserEditRequest());
        var postCount = postService.getAllUserPosts(user).size();
        model.addAttribute("postCount", postCount);
        var commentCount = commentService.getAllUserComments(user).size();
        model.addAttribute("commentCount", commentCount);
        return "profile";
    }
//    @PostMapping("/user/profile")
//    public String editUser(@ModelAttribute UserEditRequest edit, Authentication authentication) {
//        userService.editUser(edit,authentication);
//        return "profile";
//    }

    @PostMapping("/user/profile")
    public String editUser(@ModelAttribute("loggedUser") User user,
                           @RequestParam(name = "firstName", required = false) String firstName,
                           @RequestParam(name = "lastName", required = false) String lastName,
                           @RequestParam(name = "email", required = false) String email,
                           @RequestParam(name = "birthYear", required = false) Integer birthYear,
                           @RequestParam(name = "birthMonth", required = false) Integer birthMonth,
                           @RequestParam(name = "birthDay", required = false) Integer birthDay,
                           @RequestParam(name = "address", required = false) String address,
                           @RequestParam(name = "country", required = false) String country,
                           @RequestParam(name = "phone", required = false) String phone,
                           @RequestParam(name = "title", required = false) String title,
                           @RequestParam(name = "about", required = false) String about
    ) {
        UserEditRequest edit = new UserEditRequest();
        edit.setFirstName(firstName);
        edit.setLastName(lastName);
        edit.setEmail(email);
        edit.setBirthYear(birthYear);
        edit.setBirthMonth(birthMonth);
        edit.setBirthDay(birthDay);
        edit.setAddress(address);
        edit.setCountry(country);
        edit.setPhone(phone);
        edit.setTitle(title);
        edit.setAbout(about);

        userService.editUser(edit, user);
        return "redirect:/user/profile";
    }

    @GetMapping("/user/profile/posts")
    public String userPosts(@ModelAttribute(name = "loggedUser") User user, Model model) {
        var posts = postService.getAllUserPosts(user);
        model.addAttribute("posts", posts);
        return "userposts";
    }

}
