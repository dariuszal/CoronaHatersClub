package lt.codeacademy.projects.chc.coronahatersclub.controller;

import lt.codeacademy.projects.chc.coronahatersclub.requests.PostIdRequest;
import lt.codeacademy.projects.chc.coronahatersclub.requests.UserEditRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/modals")
public class ModalController {

    @GetMapping("/profile/edit")
    public String modal1(Model model) {
        model.addAttribute("edit", new UserEditRequest());
        return "modaleditprofile";
    }

    @GetMapping("/profile/posts/delete/{postId}")
    public String modal2(Model model, @PathVariable Long postId) {
        PostIdRequest request = new PostIdRequest();
        request.setPostId(postId);
        model.addAttribute("post", request);
        System.out.println("Post Id: "+postId);
        return "modaldeletepost";
    }


//    @GetMapping("/modal2")
//    public String modal2(@RequestParam("name") String name, Model model) {
//        model.addAttribute("name", name);
//        return "modal2";
//    }
}