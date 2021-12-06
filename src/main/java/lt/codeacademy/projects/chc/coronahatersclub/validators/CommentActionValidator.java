package lt.codeacademy.projects.chc.coronahatersclub.validators;

import lt.codeacademy.projects.chc.coronahatersclub.enums.UserRole;
import lt.codeacademy.projects.chc.coronahatersclub.model.Comment;
import lt.codeacademy.projects.chc.coronahatersclub.model.User;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
public class CommentActionValidator {
    public boolean deleteValidate(User user, Comment comment) {
        if (comment.getUser() == user || user.getRole().equals(UserRole.ADMIN)) {
            return true;
        } else {
            throw new AccessDeniedException("Access Denied");
        }
    }
}
