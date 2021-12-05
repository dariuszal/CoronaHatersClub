package lt.codeacademy.projects.chc.coronahatersclub.controller;

import lombok.RequiredArgsConstructor;
import lt.codeacademy.projects.chc.coronahatersclub.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private final PostService postService;

    @GetMapping("/")
    public String goHome2(Model model) {
        var posts = postService.getThreePosts();
        model.addAttribute("posts",posts);
        return"index";
    }
    @GetMapping("/index")
    public String goHome(Model model) {
        var posts = postService.getThreePosts();
        model.addAttribute("posts",posts);

        return"index";
    }
}
