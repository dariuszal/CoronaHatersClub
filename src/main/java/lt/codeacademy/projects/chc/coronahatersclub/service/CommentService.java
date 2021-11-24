package lt.codeacademy.projects.chc.coronahatersclub.service;

import lombok.RequiredArgsConstructor;
import lt.codeacademy.projects.chc.coronahatersclub.model.Comment;
import lt.codeacademy.projects.chc.coronahatersclub.model.Post;
import lt.codeacademy.projects.chc.coronahatersclub.model.User;
import lt.codeacademy.projects.chc.coronahatersclub.repository.CommentRepository;
import lt.codeacademy.projects.chc.coronahatersclub.repository.PostRepository;
import lt.codeacademy.projects.chc.coronahatersclub.repository.UserRepository;
import lt.codeacademy.projects.chc.coronahatersclub.requests.CommentRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
   private final CommentRepository commentRepository;
   private final PostRepository postRepository;
   private final UserRepository userRepository;

    public String createNewComment(CommentRequest request) {
        Post post = postRepository.findById(request.getPostId()).orElseThrow();
        User user = userRepository.findById(request.getUserId()).orElseThrow();

        Comment comment = new Comment(
                post,
                request.getBody()
        );
        comment.setUser(user);
        commentRepository.save(comment);
        return "new comment generated";
    }

    public List<Comment> getAllUserComments(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return commentRepository.findAllByUser(user);
    }
}
