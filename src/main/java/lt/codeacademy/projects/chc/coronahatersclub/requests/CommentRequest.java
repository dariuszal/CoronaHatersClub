package lt.codeacademy.projects.chc.coronahatersclub.requests;

import lombok.Data;

@Data
public class CommentRequest {
    private Long postId;
    private Long userId;
    private String body;
}
