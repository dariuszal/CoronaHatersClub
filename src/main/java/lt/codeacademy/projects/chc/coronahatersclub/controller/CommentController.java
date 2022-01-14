package lt.codeacademy.projects.chc.coronahatersclub.controller;

import lombok.RequiredArgsConstructor;
import lt.codeacademy.projects.chc.coronahatersclub.entity.User;
import lt.codeacademy.projects.chc.coronahatersclub.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/posts/{postId}/comments/new")
    public String newComment(
            @ModelAttribute(name = "loggedUser") User user,
            @PathVariable Long postId,
            @RequestParam(name = "commentBody", required = true) String body
    ) {
        commentService.createNewComment(postId,body,user);

        return "redirect:/posts";
    }
    @PostMapping("/posts/{postId}/comments/delete/{commentId}")
    public String deleteComment(
            @ModelAttribute(name = "loggedUser") User user,
            @PathVariable(name = "commentId") Long commentId
    ){
        return commentService.deleteComment(user,commentId);

    }
    @PostMapping("/posts/{postId}/comments/edit/{commentId}")
    public String editComment(
            @ModelAttribute(name = "loggedUser") User user,
            @PathVariable(name = "commentId") Long commentId,
            @RequestParam(name = "commentBody") String commentBody
    ){

        return commentService.editComment(user,commentId,commentBody);

    }
}
