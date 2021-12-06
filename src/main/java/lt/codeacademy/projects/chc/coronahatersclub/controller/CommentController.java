package lt.codeacademy.projects.chc.coronahatersclub.controller;

import lombok.RequiredArgsConstructor;
import lt.codeacademy.projects.chc.coronahatersclub.service.CommentService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/posts/{postId}/comments/new")
    public String newComment(
            @PathVariable Long postId,
            Authentication authentication,
            @RequestParam(name = "commentBody", required = true) String body
    ) {
        commentService.createNewComment(postId,body,authentication);

        return "redirect:/posts";
    }
    @PostMapping("/posts/{postId}/comments/delete/{commentId}")
    public String deleteComment(
            @PathVariable(name = "postId") Long postId,
            @PathVariable(name = "commentId") Long commentId,
            Authentication authentication
    ){

        return "redirect:/posts";
    }
}
