package ru.repos;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.domen.Post;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface PostRepository extends CrudRepository<Post,Long> {

    @Modifying
    @Query("DELETE from Post p where p.postId=:postId")
    void delete(Long postId);
}
