package lt.codeacademy.projects.chc.coronahatersclub.repository;

import lt.codeacademy.projects.chc.coronahatersclub.model.Comment;
import lt.codeacademy.projects.chc.coronahatersclub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findAllByUser(User user);

}
