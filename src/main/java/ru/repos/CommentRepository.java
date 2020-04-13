package ru.repos;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.domen.Comment;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CommentRepository extends CrudRepository<Comment,Long> {

    @Modifying
    @Query("DELETE FROM Comment c where c.commentId=:commentId")
    void delete(Long commentId);
}
