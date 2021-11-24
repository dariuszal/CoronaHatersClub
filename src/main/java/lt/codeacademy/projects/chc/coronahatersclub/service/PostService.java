package lt.codeacademy.projects.chc.coronahatersclub.service;

import lombok.RequiredArgsConstructor;
import lt.codeacademy.projects.chc.coronahatersclub.model.Post;
import lt.codeacademy.projects.chc.coronahatersclub.model.User;
import lt.codeacademy.projects.chc.coronahatersclub.repository.PostRepository;
import lt.codeacademy.projects.chc.coronahatersclub.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

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

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }
}
