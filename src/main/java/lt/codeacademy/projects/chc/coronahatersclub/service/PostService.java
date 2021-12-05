package lt.codeacademy.projects.chc.coronahatersclub.service;

import lombok.RequiredArgsConstructor;
import lt.codeacademy.projects.chc.coronahatersclub.model.Post;
import lt.codeacademy.projects.chc.coronahatersclub.model.User;
import lt.codeacademy.projects.chc.coronahatersclub.repository.PostRepository;
import lt.codeacademy.projects.chc.coronahatersclub.validators.PostDeleteValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostDeleteValidator postDeleteValidator;

    public String createNewPost(Authentication authentication, Post post) {
        User user = (User)authentication.getPrincipal();

        post.setUser(user);
        post.setCreated(LocalDateTime.now().atZone(ZoneId.of("UTC")));

        postRepository.save(post);
        return "redirect:/user/profile/posts";
    }
    public List<Post> getAllUserPosts(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return postRepository.findAllByUser(user);
    }

    public String deletePost(Long postId, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Post post = postRepository.findById(postId).orElseThrow(()->new EntityNotFoundException("Post with given Id does not exist"));
        boolean deleteValid = postDeleteValidator.validate(user,post);
        if(deleteValid) {
            postRepository.deleteById(postId);
        }
        return "redirect:/user/profile/posts";
    }

    public Page<Post> getAllPostsPageable(Integer page) {
        return postRepository.findAllOrderByDate(PageRequest.of(page,5));
    }

    public Page<Post> getThreePosts() {
        return postRepository.findAllOrderByDate(PageRequest.of(0,3));

    }
}
