package ru.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.domen.Subject;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface SubjectRepository extends CrudRepository<Subject,Long> {

    public Subject findByName(String name);
}
