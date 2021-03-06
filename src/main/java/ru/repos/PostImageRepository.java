package ru.repos;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.domen.PostImage;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface PostImageRepository extends CrudRepository<PostImage,Long> {

    @Modifying
    @Query("DELETE FROM PostImage pi where pi.imageId=:imageId")
    void delete(Long imageId);
}
