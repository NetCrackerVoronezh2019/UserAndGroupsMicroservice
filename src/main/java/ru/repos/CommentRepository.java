package ru.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.domen.Comment;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CommentRepository extends CrudRepository<Comment,Long> {
}
