package lt.codeacademy.projects.chc.coronahatersclub.validators;

import lt.codeacademy.projects.chc.coronahatersclub.enums.UserRole;
import lt.codeacademy.projects.chc.coronahatersclub.model.Post;
import lt.codeacademy.projects.chc.coronahatersclub.model.User;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
public class PostActionValidator {
    public boolean deleteValidate(User user, Post post) {
        if(post.getUser().getId() == user.getId() || user.getRole() == UserRole.ADMIN) {
            return true;
        } else {
            throw new AccessDeniedException("Access denied");
        }
    }
    public boolean editValidate(User user, Post post) {
        if(post.getUser().getId() == user.getId() || user.getRole() == UserRole.ADMIN) {
            return true;
        } else {
            throw new AccessDeniedException("Access denied");
        }
    }
}
