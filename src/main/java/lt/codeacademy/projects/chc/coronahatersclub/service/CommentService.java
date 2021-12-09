package lt.codeacademy.projects.chc.coronahatersclub.service;

import lombok.RequiredArgsConstructor;
import lt.codeacademy.projects.chc.coronahatersclub.model.Comment;
import lt.codeacademy.projects.chc.coronahatersclub.model.Post;
import lt.codeacademy.projects.chc.coronahatersclub.model.User;
import lt.codeacademy.projects.chc.coronahatersclub.repository.CommentRepository;
import lt.codeacademy.projects.chc.coronahatersclub.repository.PostRepository;
import lt.codeacademy.projects.chc.coronahatersclub.validators.CommentActionValidator;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.security.InvalidParameterException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final CommentActionValidator commentActionValidator;


    public String createNewComment(Long postId, String commentBody, User user) {
        if (commentBody.length() < 1) {
            throw new InvalidParameterException("Comment body cannot be empty");
        }
        Post post = postRepository.findById(postId).orElseThrow();

        Comment comment = new Comment(
                post,
                commentBody,
                user
        );
        post.getComments().add(comment);
        commentRepository.save(comment);
        return "new comment generated";
    }

    public List<Comment> getAllUserComments(User user) {
        return commentRepository.findAllByUser(user);
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public String deleteComment(User user, Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new EntityNotFoundException("Comment Not Found"));
        commentActionValidator.deleteValidate(user, comment);

        Post commentPost = postRepository.findById(comment.getPost().getId()).orElseThrow();
        commentPost.getComments().remove(comment);
        commentRepository.delete(comment);
        return "redirect:/posts";
    }

    public String editComment(User user, Long commentId, String commentBody) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new EntityNotFoundException("Comment Not Found"));
        boolean editValid = commentActionValidator.editValidate(user, comment, commentBody);
        if (editValid) {
            comment.setBody(commentBody);
            commentRepository.save(comment);
            return "redirect:/posts";
        }
        throw new RequestRejectedException("edit unsuccessful");
    }
}
