package ru.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.domen.Subject;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface SubjectRepository extends CrudRepository<Subject,Long> {

    public Subject findByTranslateName(String translateName);

    @Query("Select s from Subject s")
    public List<Subject> findAllSubjects();
}
