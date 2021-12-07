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

    public boolean editValidate(User user, Comment comment, String commentBody) {
        if(commentBody.length()<1) {
            return false;
        }
        if(comment.getUser() == user) {
            return true;
        } else {
            throw new AccessDeniedException("Acces Denied");
        }
    }
}
