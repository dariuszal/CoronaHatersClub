package lt.codeacademy.projects.chc.coronahatersclub.repository;

import lt.codeacademy.projects.chc.coronahatersclub.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
