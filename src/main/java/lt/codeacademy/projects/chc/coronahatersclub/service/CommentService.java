package lt.codeacademy.projects.chc.coronahatersclub.service;

import lombok.RequiredArgsConstructor;
import lt.codeacademy.projects.chc.coronahatersclub.model.Comment;
import lt.codeacademy.projects.chc.coronahatersclub.model.Post;
import lt.codeacademy.projects.chc.coronahatersclub.model.User;
import lt.codeacademy.projects.chc.coronahatersclub.repository.CommentRepository;
import lt.codeacademy.projects.chc.coronahatersclub.repository.PostRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
   private final CommentRepository commentRepository;
   private final PostRepository postRepository;


    public String createNewComment(Long postId, String commentBody, Authentication authentication) {
        if(commentBody.length() < 1) {
            throw new InvalidParameterException("Comment body cannot be empty");
        }
        Post post = postRepository.findById(postId).orElseThrow();
        User user = (User) authentication.getPrincipal();

        Comment comment = new Comment(
                post,
                commentBody,
                user
        );
        post.getComments().add(comment);
        commentRepository.save(comment);
        return "new comment generated";
    }

    public List<Comment> getAllUserComments(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return commentRepository.findAllByUser(user);
    }
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }
}
