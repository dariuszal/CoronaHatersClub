package lt.codeacademy.projects.chc.coronahatersclub.validator;

import lombok.RequiredArgsConstructor;
import lt.codeacademy.projects.chc.coronahatersclub.entity.User;
import lt.codeacademy.projects.chc.coronahatersclub.repository.UserRepository;
import lt.codeacademy.projects.chc.coronahatersclub.dto.UserEditRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserActionValidator {

    private final UserRepository userRepository;

    public boolean validateRegister(User user) {
        if(userRepository.findByUsernameOrEmail(user.getUsername(),user.getEmail()).isPresent()) {
            throw new IllegalStateException("Someone is already registered with this email or username");
        } else return true;
    }

    public User validateEdit(User user, UserEditRequest edit, LocalDate newDate){
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
            user.setProfileTitle(edit.getTitle());
        }
        if (edit.getAbout() != null && !edit.getAbout().isEmpty()) {
            user.setAboutMe(edit.getAbout());
        }
        return user;
    }

}
