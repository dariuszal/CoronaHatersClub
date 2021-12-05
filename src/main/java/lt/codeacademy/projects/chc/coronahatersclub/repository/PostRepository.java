package lt.codeacademy.projects.chc.coronahatersclub.repository;

import lt.codeacademy.projects.chc.coronahatersclub.model.Post;
import lt.codeacademy.projects.chc.coronahatersclub.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findAllByUser(User user);

    @Query(value = "SELECT * FROM post p ORDER BY p.created desc",nativeQuery = true)
    Page<Post> findAllOrderByDate(Pageable pageable);
}
