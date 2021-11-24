//package lt.codeacademy.projects.chc.coronahatersclub.controller;
//
//import lombok.RequiredArgsConstructor;
//import lt.codeacademy.projects.chc.coronahatersclub.requests.CommentRequest;
//import lt.codeacademy.projects.chc.coronahatersclub.service.CommentService;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("api/v1/comments")
//public class CommentController {
//
//    private final CommentService commentService;
//
//    @PostMapping
//    public String newComment(@RequestBody CommentRequest request) {
//        return commentService.createNewComment(request);
//    }
//}
