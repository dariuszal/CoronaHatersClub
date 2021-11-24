package lt.codeacademy.projects.chc.coronahatersclub.controller;

import lombok.RequiredArgsConstructor;
import lt.codeacademy.projects.chc.coronahatersclub.model.Post;
import lt.codeacademy.projects.chc.coronahatersclub.service.PostService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    @GetMapping("/new")
    public String createNewPost(Model model) {
        model.addAttribute("post", new Post());
        return "newpost";
    }

    @PostMapping("/new")
    public String createNewPost(@ModelAttribute Post post, BindingResult errors, Authentication authentication) {
        if(errors.hasErrors()) {
            return "/posts/new";
        }
        return postService.createNewPost(authentication,post);
    }

}
