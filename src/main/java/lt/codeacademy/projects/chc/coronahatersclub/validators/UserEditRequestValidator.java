package lt.codeacademy.projects.chc.coronahatersclub.validators;

import lt.codeacademy.projects.chc.coronahatersclub.model.User;
import lt.codeacademy.projects.chc.coronahatersclub.requests.UserEditRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserEditRequestValidator {
    public User  validate(User user, UserEditRequest edit,LocalDate newDate){
        if(edit.getFirstName() !=null && !edit.getFirstName().equals("")) {
            user.setFirstName(edit.getFirstName());
        }
        if (edit.getLastName() != null && !edit.getLastName().equals("")) {
            user.setLastName(edit.getLastName());

        }
        if (edit.getAddress() != null && !edit.getAddress().equals("")) {
            user.setAddress(edit.getAddress());
        }
        if (newDate != null && newDate.isBefore(LocalDate.now())) {
            user.setBirthdate(newDate);
        }
        if (edit.getPhone() != null && edit.getPhone() != "") {
            user.setPhoneNumber(edit.getPhone());
        }
        if (edit.getCountry() != null && !edit.getCountry().equals("")) {
            user.setCountry(edit.getCountry());
        }
        if (edit.getTitle() != null && !edit.getTitle().equals("")) {
            user.setTitle(edit.getTitle());
        }
        if (edit.getAbout() != null && !edit.getAbout().isEmpty()) {
            user.setAboutMe(edit.getAbout());
        }
        return user;
    }
}
