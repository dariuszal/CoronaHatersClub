package lt.codeacademy.projects.chc.coronahatersclub.controller;

import lombok.RequiredArgsConstructor;
import lt.codeacademy.projects.chc.coronahatersclub.model.Post;
import lt.codeacademy.projects.chc.coronahatersclub.requests.PostIdRequest;
import lt.codeacademy.projects.chc.coronahatersclub.service.PostService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    @GetMapping
    public String posts(@RequestParam(name = "page", required = false) Integer page, Model model) {
        if (page == null) page = 0;
        var posts = postService.getAllPostsPageable(page);
        model.addAttribute("posts", posts);
        model.addAttribute("currentPage", page);

        return "posts";
    }

    @GetMapping("/new")
    public String createNewPost(Model model) {
        model.addAttribute("post", new Post());
        return "newpost";
    }

    @PostMapping("/new")
    public String createNewPost(@ModelAttribute Post post, BindingResult errors, Authentication authentication) {
        if (errors.hasErrors()) {
            return "/posts/new";
        }
        return postService.createNewPost(authentication, post);
    }

    @PostMapping("/delete")
    public String deletePost(@ModelAttribute PostIdRequest post, Authentication authentication) {
        return postService.deletePost(post.getPostId(), authentication);
    }

}
