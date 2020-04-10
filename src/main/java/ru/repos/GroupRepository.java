package ru.repos;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.domen.Group;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface GroupRepository extends CrudRepository<Group,Long> {
    @Override
    @Modifying
    public void deleteById(Long aLong);
    @Query("SELECT g from Group g WHERE g.name Like %:name%")
    public List<Group> findByName(String name);
}
