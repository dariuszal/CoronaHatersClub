package lt.codeacademy.projects.chc.coronahatersclub.requests;

import lombok.Data;

//TODO Validation/ Custom Validation // Thymeleaf Option tag for countries and populate country phone code/ birthdate parse.
@Data
public class UserEditRequest {
    private String firstName;
    private String lastName;
    private String email;
    private Integer birthYear;
    private Integer birthMonth;
    private Integer birthDay;
    private String address;
    private String country;
    private String phone;
    private String title;
    private String about;
}
