package ru.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.domen.Post;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface PostRepository extends CrudRepository<Post,Long> {
}
