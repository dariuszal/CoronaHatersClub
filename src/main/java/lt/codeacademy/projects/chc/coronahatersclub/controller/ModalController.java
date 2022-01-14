package lt.codeacademy.projects.chc.coronahatersclub.controller;

import lombok.RequiredArgsConstructor;
import lt.codeacademy.projects.chc.coronahatersclub.convertor.UserToUserEditRequest;
import lt.codeacademy.projects.chc.coronahatersclub.entity.User;
import lt.codeacademy.projects.chc.coronahatersclub.dto.PostIdRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/modals")
public class ModalController {

    private final UserToUserEditRequest userToUserEditRequest;

    @GetMapping("/profile/edit")
    public String modal1(@ModelAttribute(name = "loggedUser") User user, Model model) {
        var userProfile = userToUserEditRequest.convert(user);
        model.addAttribute("userProfile",userProfile);
        return "modaleditprofile";
    }
    @GetMapping("/posts/delete/{postId}")
    public String modal2(Model model, @PathVariable Long postId) {
        PostIdRequest request = new PostIdRequest();
        request.setPostId(postId);
        model.addAttribute("post", request);
        return "modaldeletepost";
    }

}