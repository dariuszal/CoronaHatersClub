package lt.codeacademy.projects.chc.coronahatersclub.convertor;

import lt.codeacademy.projects.chc.coronahatersclub.entity.User;
import lt.codeacademy.projects.chc.coronahatersclub.dto.UserEditRequest;
import org.springframework.stereotype.Service;

@Service
public class UserToUserEditRequest {
    public UserEditRequest convert (User user) {
        UserEditRequest edit = new UserEditRequest();
        edit.setFirstName(user.getFirstName());
        edit.setLastName(user.getLastName());
        edit.setEmail(user.getEmail());
        if(user.getBirthdate()!=null) {
            edit.setBirthYear(user.getBirthdate().getYear());
            edit.setBirthMonth(user.getBirthdate().getMonthValue());
            edit.setBirthDay(user.getBirthdate().getDayOfMonth());
        }
        edit.setAddress(user.getAddress());
        edit.setCountry(user.getCountry());
        edit.setPhone(user.getPhoneNumber());
        edit.setTitle(user.getProfileTitle());
        edit.setAbout(user.getAboutMe());

        return edit;

    }
}
