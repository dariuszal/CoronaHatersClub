package lt.codeacademy.projects.chc.coronahatersclub.dto;

import lombok.Data;

@Data
public class PostRequest {
    private Long userId;
    private String title;
    private String body;
}
