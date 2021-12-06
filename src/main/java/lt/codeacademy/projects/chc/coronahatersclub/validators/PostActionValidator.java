package lt.codeacademy.projects.chc.coronahatersclub.validators;

import lt.codeacademy.projects.chc.coronahatersclub.enums.UserRole;
import lt.codeacademy.projects.chc.coronahatersclub.model.Post;
import lt.codeacademy.projects.chc.coronahatersclub.model.User;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;

@Service
public class PostActionValidator {
    public boolean deleteValidate(User user) {
        if(user.getRole() == UserRole.ADMIN) {
            return true;
        } else {
            throw new AccessDeniedException("Access denied");
        }
    }
    public boolean editValidate(User user, Post post, String postTitle, String postBody) {
        if(post.getUser().getId() == user.getId() || user.getRole() == UserRole.ADMIN) {
            if(postTitle.length()>0 && postBody.length() > 0){
                return true;
            } else {
                throw new InvalidParameterException("Post body or title cannot be empty");
            }
        }
        else {
            throw new AccessDeniedException("Access denied");
        }
    }
}
