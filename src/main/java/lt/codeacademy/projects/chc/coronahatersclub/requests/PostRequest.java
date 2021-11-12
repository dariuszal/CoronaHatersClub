package lt.codeacademy.projects.chc.coronahatersclub.requests;

import lombok.Data;

@Data
public class PostRequest {
    private Long userId;
    private String title;
    private String body;
}
