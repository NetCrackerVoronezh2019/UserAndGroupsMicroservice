package ru.repos;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.domen.Group;

import javax.transaction.Transactional;

@Repository
@Transactional

public interface GroupRepository extends CrudRepository<Group,Long> {
    @Override
    @Modifying
    void deleteById(Long aLong);
}
