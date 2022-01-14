package lt.codeacademy.projects.chc.coronahatersclub.dto;

import lombok.Data;

@Data
public class UserRequest {
    private String username;
    private String password;
    private String email;

    public UserRequest(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
