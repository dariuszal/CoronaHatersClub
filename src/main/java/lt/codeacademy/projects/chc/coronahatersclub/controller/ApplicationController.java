package lt.codeacademy.projects.chc.coronahatersclub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {

    @GetMapping("/login")
    public String login() {
        return"login";
    }
    @GetMapping("/tester")
    public String test() {
        return "tester";
    }

}
