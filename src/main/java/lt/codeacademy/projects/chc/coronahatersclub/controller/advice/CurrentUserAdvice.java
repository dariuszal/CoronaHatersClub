package lt.codeacademy.projects.chc.coronahatersclub.controller.advice;

import lombok.RequiredArgsConstructor;
import lt.codeacademy.projects.chc.coronahatersclub.model.User;
import lt.codeacademy.projects.chc.coronahatersclub.repository.UserRepository;
import lt.codeacademy.projects.chc.coronahatersclub.security.UserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice(annotations = Controller.class)
@RequiredArgsConstructor
public class CurrentUserAdvice {
    private final UserRepository userRepository;

    @ModelAttribute("loggedUser")
    public User getCurrentUser() {
        try{
            var principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return userRepository.findById(principal.getId()).orElseThrow(()->new EntityNotFoundException("User not found"));
        } catch (Exception e) {
            return null;
        }
    }
}
