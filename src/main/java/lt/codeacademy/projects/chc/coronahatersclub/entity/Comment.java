package lt.codeacademy.projects.chc.coronahatersclub.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(length = 500)
    private String body;
    private LocalDateTime date;
    private LocalDateTime lastUpdated;

    public Comment(Post post, String body, User user) {
        this.post = post;
        this.body = body;
        this.user = user;
        this.date = LocalDateTime.now();
    }
}
