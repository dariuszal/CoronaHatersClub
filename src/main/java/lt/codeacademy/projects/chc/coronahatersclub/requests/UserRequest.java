package lt.codeacademy.projects.chc.coronahatersclub.requests;

import lombok.Data;
import lt.codeacademy.projects.chc.coronahatersclub.enums.UserRole;

@Data
public class UserRequest {
    private String username;
    private String password;
    private String email;
    private UserRole role;

    public UserRequest(String username, String password, String email, UserRole role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}
