package lt.codeacademy.projects.chc.coronahatersclub.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

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
    private LocalDate date;

    public Comment(Post post, String body) {
        this.post = post;
        this.body = body;
        this.date = LocalDate.now();
    }
}
