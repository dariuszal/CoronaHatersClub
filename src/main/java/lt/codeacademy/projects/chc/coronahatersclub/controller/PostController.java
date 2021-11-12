package lt.codeacademy.projects.chc.coronahatersclub.controller;

import lombok.RequiredArgsConstructor;
import lt.codeacademy.projects.chc.coronahatersclub.requests.PostRequest;
import lt.codeacademy.projects.chc.coronahatersclub.service.PostService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/posts")
public class PostController {
    private final PostService postService;

    @PostMapping
    public String createNewPost(@RequestBody PostRequest request) {
        return postService.createNewPost(request);
    }

}