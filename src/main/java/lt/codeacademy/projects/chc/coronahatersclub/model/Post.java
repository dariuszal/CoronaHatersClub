package lt.codeacademy.projects.chc.coronahatersclub.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

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
    @Column(length = 25)
    private String title;
    @Column(length = 1000)
    private String body;
    private ZonedDateTime created;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    public Post(User user, String title, String body) {
        this.user = user;
        this.title = title;
        this.body = body;
        LocalDateTime.now().atZone(ZoneId.of("UTC"));
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", user=" + user +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", created=" + created +
                '}';
    }
}
