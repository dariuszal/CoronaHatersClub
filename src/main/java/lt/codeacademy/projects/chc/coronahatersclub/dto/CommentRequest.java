package lt.codeacademy.projects.chc.coronahatersclub.dto;

import lombok.Data;

@Data
public class CommentRequest {
    private Long postId;
    private Long userId;
    private String body;
}
