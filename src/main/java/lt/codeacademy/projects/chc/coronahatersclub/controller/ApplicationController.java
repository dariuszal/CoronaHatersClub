package lt.codeacademy.projects.chc.coronahatersclub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {

    @GetMapping("/")
    public String goHome2() {
        return"index";
    }
    @GetMapping("/index")
    public String goHome() {
        return"index";
    }
    @GetMapping("/login")
    public String login() {
        return"login";
    }
    @GetMapping("/posts")
    public String posts() {
        return "posts";
    }
    @GetMapping("/tester")
    public String test() {
        return "tester";
    }

}
