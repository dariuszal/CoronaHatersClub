package lt.codeacademy.projects.chc.coronahatersclub.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "posted_by")
    private User user;
    private String title;
    private String body;
    private LocalDate date;

    public Post(User user, String title, String body) {
        this.user = user;
        this.title = title;
        this.body = body;
        this.date = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", user=" + user +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", date=" + date +
                '}';
    }
}
